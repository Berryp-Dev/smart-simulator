package com.capgemini.smart.simulator.raccordopartitario.conti.common.adapter.in.rest.advice;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.adapter.in.rest.dto.ErrorResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.common.adapter.in.rest.dto.model.ServiceErrorDto;
import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Traduce {@link ServiceNotImplementedException} in HTTP 501 con il body dello YAML
 * (Error501 -> ErrorResponse con mainError.errorMessage). Limitato ai RestResource del modulo e
 * con precedenza sul GlobalExceptionHandler del core. Non gestisce gli altri percorsi di errore
 * (JSON malformato, path inesistente, ecc.), che restano del GlobalExceptionHandler.
 */
@RestControllerAdvice(basePackages = "com.capgemini.smart.simulator.raccordopartitario.conti")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RaccordoPartitarioContiServiceNotImplementedExceptionHandler {

    @ExceptionHandler(ServiceNotImplementedException.class)
    public ResponseEntity<ErrorResponse> handleNotImplemented(ServiceNotImplementedException ex) {
        ServiceErrorDto mainError = new ServiceErrorDto();
        mainError.setErrorMessage(ex.getMessage());
        ErrorResponse body = new ErrorResponse();
        body.setMainError(mainError);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.APPLICATION_JSON).body(body);
    }
}
