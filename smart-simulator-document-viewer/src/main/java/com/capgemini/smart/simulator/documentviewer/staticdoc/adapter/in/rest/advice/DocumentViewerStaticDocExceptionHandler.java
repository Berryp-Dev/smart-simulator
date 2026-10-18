package com.capgemini.smart.simulator.documentviewer.staticdoc.adapter.in.rest.advice;

import com.capgemini.smart.simulator.core.rest.ApiError;
import com.capgemini.smart.simulator.core.validation.ValidationError;
import com.capgemini.smart.simulator.documentviewer.staticdoc.adapter.in.rest.resource.DocumentViewerStaticDocRestResource;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.exception.DocumentNotFoundException;
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
 * Gestione del 404 dello scenario Static Doc, limitata alla sua RestResource.
 * Precede il GlobalExceptionHandler del core (che altrimenti catturerebbe
 * l'eccezione con il proprio handler generico). Stessa forma di ApiError
 * degli errori 400 del core, con status ed error propri.
 */
@RestControllerAdvice(assignableTypes = DocumentViewerStaticDocRestResource.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DocumentViewerStaticDocExceptionHandler {

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
