package com.capgemini.smart.simulator.core.rest;

import com.capgemini.smart.simulator.core.exception.ValidationException;
import com.capgemini.smart.simulator.core.validation.ValidationError;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Gestione centralizzata degli errori per tutti i controller REST dei moduli
 * sottosistema (attiva automaticamente via component scan del package
 * com.capgemini.smart.simulator.core in ogni modulo che dichiara la Application
 * class nel package radice com.capgemini.smart.simulator.*).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ex, request, null);
    }

    /**
     * Handler standard di default per gli errori di validazione. Non si applica
     * ai controller marcati con {@code @ReplicatesOriginalBehavior}, che
     * gestiscono un proprio formato errori.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidation(ValidationException ex, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ex, request, ex.getErrors());
    }

    /**
     * Valore non convertibile al tipo del campo target (deserializzazione
     * Jackson fallita prima del validator, per qualsiasi tipo target: enum,
     * numerico, ecc.): HTTP 400 con un solo ValidationError (campo e
     * messaggio specifico per il tipo). Per ogni altra causa di
     * HttpMessageNotReadableException (JSON malformato, body assente, tipo
     * sbagliato per un'intera struttura) risponde comunque HTTP 400, con un
     * messaggio fisso che non espone il messaggio di Jackson (vedi
     * {@link #handleMalformedBody}).
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMessageNotReadable(HttpMessageNotReadableException ex,
                                                             HttpServletRequest request) {
        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            ValidationError validationError = buildInvalidFormatError(invalidFormatException);
            List<ValidationError> validationErrors = List.of(validationError);
            return build(HttpStatus.BAD_REQUEST, resolveRequestNotValidMessage(validationErrors), request, validationErrors);
        }
        return handleMalformedBody(ex, request);
    }

    /**
     * Corpo della richiesta non leggibile per una causa diversa da
     * InvalidFormatException (JSON malformato, body assente, tipo sbagliato
     * per un'intera struttura): errore del chiamante, non del server, quindi
     * log a livello warn (non error) e HTTP 400 invece di 500. Il messaggio
     * e' fisso, senza esporre il messaggio di Jackson ne' nomi di classi
     * interne.
     */
    private ResponseEntity<ApiError> handleMalformedBody(HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.warn("Corpo della richiesta non leggibile su {}: {}", request.getRequestURI(), ex.getMessage());
        String fieldPath = resolveMalformedBodyFieldPath(ex);
        ValidationError validationError = new ValidationError(fieldPath, "Corpo della richiesta non valido o malformato");
        List<ValidationError> validationErrors = List.of(validationError);
        return build(HttpStatus.BAD_REQUEST, resolveRequestNotValidMessage(validationErrors), request, validationErrors);
    }

    /**
     * Messaggio di primo livello per {@code ApiError.message} nei rami di
     * {@code handleMessageNotReadable}: stesso stile fisso e non-Jackson gia'
     * usato da {@code ValidationException} (vedi costruttore), con cui questi
     * rami condividono la stessa natura di errore 400 con lista {@code errors}.
     */
    private String resolveRequestNotValidMessage(List<ValidationError> validationErrors) {
        return "Richiesta non valida: " + validationErrors.size() + " errore/i";
    }

    private String resolveMalformedBodyFieldPath(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof JsonMappingException jsonMappingException && !jsonMappingException.getPath().isEmpty()) {
            return resolveFieldPath(jsonMappingException);
        }
        return null;
    }

    /**
     * Path senza handler ne' risorsa statica (es. URL inesistente, favicon.ico):
     * errore del chiamante, HTTP 404 con ApiError dal messaggio fisso. Log a
     * livello warn senza stacktrace, come per il corpo non leggibile.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoResourceFound(NoResourceFoundException ex, HttpServletRequest request) {
        log.warn("Risorsa non trovata su {}", request.getRequestURI());
        return build(HttpStatus.NOT_FOUND, "Risorsa non trovata", request, null);
    }

    /**
     * Metodo HTTP non ammesso sul path: HTTP 405 con ApiError dal messaggio fisso
     * e gli header forniti dall'eccezione (es. Allow). Log a livello warn senza
     * stacktrace.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                             HttpServletRequest request) {
        log.warn("Metodo HTTP non supportato su {}: {}", request.getRequestURI(), request.getMethod());
        return withHeaders(build(HttpStatus.METHOD_NOT_ALLOWED, "Metodo HTTP non supportato", request, null),
                ex.getHeaders());
    }

    /**
     * Content-Type della richiesta non supportato: HTTP 415 con ApiError dal
     * messaggio fisso e gli header forniti dall'eccezione (es. Accept). Log a
     * livello warn senza stacktrace.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiError> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                HttpServletRequest request) {
        log.warn("Tipo di contenuto non supportato su {}: {}", request.getRequestURI(), request.getContentType());
        return withHeaders(build(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo di contenuto non supportato", request, null),
                ex.getHeaders());
    }

    /**
     * Formato di risposta richiesto (header Accept) non producibile: HTTP 406 con
     * ApiError dal messaggio fisso. Se il corpo non e' scrivibile nel formato
     * richiesto da Accept, Spring risponde con il solo status. Log a livello warn
     * senza stacktrace.
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ApiError> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                 HttpServletRequest request) {
        log.warn("Formato di risposta non accettabile su {}: {}", request.getRequestURI(), request.getHeader(HttpHeaders.ACCEPT));
        return withHeaders(build(HttpStatus.NOT_ACCEPTABLE, "Formato di risposta non accettabile", request, null),
                ex.getHeaders());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
        log.error("Errore non gestito su {}: {}", request.getRequestURI(), ex.getMessage(), ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex, request, null);
    }

    private ValidationError buildInvalidFormatError(InvalidFormatException invalidFormatException) {
        String fieldPath = resolveFieldPath(invalidFormatException);
        String message = resolveInvalidFormatMessage(invalidFormatException);
        return new ValidationError(fieldPath, message);
    }

    private String resolveInvalidFormatMessage(InvalidFormatException invalidFormatException) {
        Class<?> targetType = invalidFormatException.getTargetType();
        if (targetType.isEnum()) {
            String admittedValues = resolveAdmittedValues(targetType);
            return "Valore non ammesso: '" + invalidFormatException.getValue() + "'. Valori ammessi: " + admittedValues;
        }
        return "Valore non valido: '" + invalidFormatException.getValue() + "'. Tipo atteso: " + targetType.getSimpleName();
    }

    private String resolveFieldPath(JsonMappingException jsonMappingException) {
        StringBuilder fieldPath = new StringBuilder();
        for (JsonMappingException.Reference reference : jsonMappingException.getPath()) {
            if (reference.getFieldName() != null) {
                if (fieldPath.length() > 0) {
                    fieldPath.append('.');
                }
                fieldPath.append(reference.getFieldName());
            } else {
                fieldPath.append('[').append(reference.getIndex()).append(']');
            }
        }
        return fieldPath.toString();
    }

    private String resolveAdmittedValues(Class<?> enumType) {
        return Arrays.stream(enumType.getEnumConstants())
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    /** Aggiunge alla risposta gia' costruita gli header forniti dall'eccezione. */
    private ResponseEntity<ApiError> withHeaders(ResponseEntity<ApiError> response, HttpHeaders headers) {
        return ResponseEntity.status(response.getStatusCode()).headers(headers).body(response.getBody());
    }

    private ResponseEntity<ApiError> build(HttpStatus status, Exception ex, HttpServletRequest request,
                                           List<ValidationError> validationErrors) {
        return build(status, ex.getMessage(), request, validationErrors);
    }

    private ResponseEntity<ApiError> build(HttpStatus status, String message, HttpServletRequest request,
                                           List<ValidationError> validationErrors) {
        ApiError body = new ApiError(Instant.now(), status.value(), status.getReasonPhrase(),
                message, request.getRequestURI(), validationErrors);
        return ResponseEntity.status(status).body(body);
    }
}
