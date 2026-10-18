package com.capgemini.smart.simulator.documentviewer.document.getstatic.adapter.in.rest.advice;

import com.capgemini.smart.simulator.core.rest.ApiError;
import com.capgemini.smart.simulator.core.validation.ValidationError;
import com.capgemini.smart.simulator.documentviewer.common.domain.exception.DocumentNotFoundException;
import com.capgemini.smart.simulator.documentviewer.document.getstatic.adapter.in.rest.resource.DocumentViewerDocumentGetStaticRestResource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

/**
 * Gestione del 404 dell'API document, limitata alla sua RestResource.
 * Precede il GlobalExceptionHandler del core (che altrimenti catturerebbe
 * l'eccezione con il proprio handler generico). Stessa forma di ApiError
 * degli errori 400 del core, con status ed error propri.
 */
@RestControllerAdvice(assignableTypes = DocumentViewerDocumentGetStaticRestResource.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DocumentViewerDocumentGetStaticExceptionHandler {

    private static final String MESSAGE = "Risorsa non trovata: 1 errore/i";
    private static final String ERROR_DETAIL = "Documento non trovato";

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(DocumentNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<ValidationError> errors = List.of(new ValidationError(null, ERROR_DETAIL));
        ApiError body = new ApiError(Instant.now(), status.value(), status.getReasonPhrase(),
                MESSAGE, request.getRequestURI(), errors);
        return ResponseEntity.status(status).body(body);
    }
}
