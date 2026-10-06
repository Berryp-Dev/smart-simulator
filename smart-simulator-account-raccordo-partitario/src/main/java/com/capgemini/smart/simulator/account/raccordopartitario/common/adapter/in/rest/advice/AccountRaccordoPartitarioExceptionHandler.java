package com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.advice;

import com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.dto.AccountRaccordoPartitarioErrorBody;
import com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.dto.AccountRaccordoPartitarioErrorBody.ErrorItem;
import com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.dto.AccountRaccordoPartitarioErrorBody.Payload;
import com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.exception.NotImplementedOperationException;
import com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.resource.AccountRaccordoPartitarioCurrentAccountConfigureResource;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.resource.AccountRaccordoPartitarioSalesResource;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Gestione errori delle Resource del modulo (REPLICA ORIGINALE): risponde con
 * la busta {@code header + payload} dichiarata dagli YAML, non con
 * {@code ApiError} del core. Ha priorita' su {@code GlobalExceptionHandler}
 * per le sole Resource indicate.
 * <p>
 * Origine dei valori: {@code errorMessage} 400 = description di Error400 e 501 =
 * description di Error501 (components-v1.2.2.yaml); {@code errorCode} = status
 * come stringa; {@code technicalDescription} dei 400 = percorso del campo +
 * messaggio (default); quella del 501 e' il fallback "Servizio non implementato".
 */
@RestControllerAdvice(assignableTypes = {
        AccountRaccordoPartitarioSalesResource.class,
        AccountRaccordoPartitarioCurrentAccountConfigureResource.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccountRaccordoPartitarioExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AccountRaccordoPartitarioExceptionHandler.class);

    private static final String BAD_REQUEST_MESSAGE = "Bad Request.";
    private static final String NOT_IMPLEMENTED_MESSAGE = "Not implemented.";
    private static final String NOT_IMPLEMENTED_TECHNICAL_DESCRIPTION = "Servizio non implementato";
    private static final String MALFORMED_BODY_DESCRIPTION = "Corpo della richiesta non valido o malformato";

    private final ObjectMapper objectMapper;

    public AccountRaccordoPartitarioExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(NotImplementedOperationException.class)
    public ResponseEntity<AccountRaccordoPartitarioErrorBody> handleNotImplemented(NotImplementedOperationException ex) {
        ErrorItem mainError = new ErrorItem(String.valueOf(HttpStatus.NOT_IMPLEMENTED.value()),
                NOT_IMPLEMENTED_MESSAGE, NOT_IMPLEMENTED_TECHNICAL_DESCRIPTION);
        return build(HttpStatus.NOT_IMPLEMENTED, ex.getRequest(), new Payload(mainError, null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AccountRaccordoPartitarioErrorBody> handleBeanValidation(MethodArgumentNotValidException ex) {
        List<ErrorItem> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::mapToErrorItem)
                .toList();
        return buildBadRequest(ex.getBindingResult().getTarget(), errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<AccountRaccordoPartitarioErrorBody> handleConstraintViolation(ConstraintViolationException ex) {
        List<ErrorItem> errors = ex.getConstraintViolations().stream()
                .map(this::mapToErrorItem)
                .toList();
        return buildBadRequest(null, errors);
    }

    /**
     * Deserializzazione fallita o corpo non leggibile (JSON malformato, body
     * assente, tipo errato): sempre HTTP 400. Il corpo non e' leggibile,
     * quindi l'header viene generato.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AccountRaccordoPartitarioErrorBody> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        log.warn("Corpo della richiesta non leggibile: {}", ex.getMessage());
        String fieldPath = resolveFieldPath(ex.getCause());
        String description = resolveMessageNotReadableDescription(ex.getCause(), fieldPath);
        return buildBadRequest(null, List.of(buildBadRequestItem(fieldPath, description)));
    }

    /**
     * Valore non convertibile in un campo noto (enum non ammesso, tipo errato):
     * descrizione specifica; altrimenti messaggio generico, senza esporre
     * il messaggio di Jackson.
     */
    private String resolveMessageNotReadableDescription(Throwable cause, String fieldPath) {
        if (cause instanceof InvalidFormatException invalidFormat) {
            return "Valore non valido: '" + invalidFormat.getValue() + "'";
        }
        if (fieldPath != null && !fieldPath.isEmpty() && cause instanceof ValueInstantiationException) {
            return "Valore non valido";
        }
        return MALFORMED_BODY_DESCRIPTION;
    }

    private ErrorItem mapToErrorItem(ObjectError error) {
        String fieldPath = error instanceof FieldError fieldError ? fieldError.getField() : error.getObjectName();
        return buildBadRequestItem(fieldPath, error.getDefaultMessage());
    }

    private ErrorItem mapToErrorItem(ConstraintViolation<?> violation) {
        return buildBadRequestItem(violation.getPropertyPath().toString(), violation.getMessage());
    }

    private ErrorItem buildBadRequestItem(String fieldPath, String message) {
        String technicalDescription = fieldPath == null || fieldPath.isEmpty() ? message : fieldPath + ": " + message;
        return new ErrorItem(String.valueOf(HttpStatus.BAD_REQUEST.value()), BAD_REQUEST_MESSAGE, technicalDescription);
    }

    /** L'errore principale e' il primo rilevato; errorList contiene tutte le violazioni. */
    private ResponseEntity<AccountRaccordoPartitarioErrorBody> buildBadRequest(Object request, List<ErrorItem> errors) {
        ErrorItem mainError = errors.isEmpty() ? buildBadRequestItem(null, MALFORMED_BODY_DESCRIPTION) : errors.get(0);
        return build(HttpStatus.BAD_REQUEST, request, new Payload(mainError, errors));
    }

    private ResponseEntity<AccountRaccordoPartitarioErrorBody> build(HttpStatus status, Object request, Payload payload) {
        return ResponseEntity.status(status).body(new AccountRaccordoPartitarioErrorBody(resolveHeader(request), payload));
    }

    /** Riecheggia l'header della richiesta se presente, altrimenti lo genera (timestamp e trackId nuovi). */
    private JsonNode resolveHeader(Object request) {
        if (request != null) {
            JsonNode requestHeader = objectMapper.valueToTree(request).get("header");
            if (requestHeader instanceof ObjectNode headerNode) {
                // i modelli generati serializzano anche i campi null: l'eco contiene solo quelli ricevuti
                headerNode.properties().removeIf(property -> property.getValue().isNull());
                if (!headerNode.isEmpty()) {
                    return headerNode;
                }
            }
        }
        ObjectNode generatedHeader = objectMapper.createObjectNode();
        generatedHeader.put("timestamp", Instant.now().toString());
        generatedHeader.put("trackId", UUID.randomUUID().toString());
        return generatedHeader;
    }

    private String resolveFieldPath(Throwable cause) {
        if (!(cause instanceof JsonMappingException jsonMappingException)) {
            return null;
        }
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
}
