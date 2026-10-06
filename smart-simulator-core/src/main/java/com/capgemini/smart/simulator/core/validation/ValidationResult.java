package com.capgemini.smart.simulator.core.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Esito di una validazione. Accumula TUTTI gli errori riscontrati (non
 * fail-fast): un validator esegue ogni controllo previsto e raccoglie ogni
 * violazione, senza fermarsi alla prima.
 */
public final class ValidationResult {

    private final List<ValidationError> errors;

    private ValidationResult(List<ValidationError> errors) {
        this.errors = List.copyOf(errors);
    }

    /** Costruisce il risultato aggregando una lista di errori (lista vuota = valido). */
    public static ValidationResult of(List<ValidationError> errors) {
        return new ValidationResult(errors);
    }

    /** Restituisce un builder per accumulare gli errori progressivamente. */
    public static Builder builder() {
        return new Builder();
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    /** True se non e' stato riscontrato alcun errore. */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /** Builder per l'accumulo degli errori di validazione. */
    public static final class Builder {

        private final List<ValidationError> errors = new ArrayList<>();

        private Builder() {
        }

        public Builder insertError(String field, String message) {
            errors.add(new ValidationError(field, message));
            return this;
        }

        public ValidationResult build() {
            return new ValidationResult(errors);
        }
    }
}
