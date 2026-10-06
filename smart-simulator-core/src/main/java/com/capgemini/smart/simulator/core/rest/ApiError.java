package com.capgemini.smart.simulator.core.rest;

import com.capgemini.smart.simulator.core.validation.ValidationError;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

/**
 * Corpo standard di risposta per gli errori REST, comune a tutti i moduli
 * sottosistema. Il campo {@code errors} e' valorizzato solo per gli errori
 * di validazione (null altrimenti).
 */
public record ApiError(Instant timestamp, int status, String error, String message, String path,
                       @JsonInclude(JsonInclude.Include.NON_NULL) List<ValidationError> errors) {
}
