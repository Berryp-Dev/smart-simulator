package com.capgemini.smart.simulator.core.exception;

import com.capgemini.smart.simulator.core.validation.ValidationError;
import com.capgemini.smart.simulator.core.validation.ValidationResult;

import java.util.List;

/**
 * Sollevata dai service applicativi quando la validazione di uno scenario
 * fallisce ({@code validationResult.isValid() == false}).
 */
public class ValidationException extends RuntimeException {

    private final transient ValidationResult validationResult;

    public ValidationException(ValidationResult validationResult) {
        super("Validazione fallita: " + validationResult.getErrors().size() + " errore/i");
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public List<ValidationError> getErrors() {
        return validationResult.getErrors();
    }
}
