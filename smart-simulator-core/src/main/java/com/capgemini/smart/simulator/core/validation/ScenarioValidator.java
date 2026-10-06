package com.capgemini.smart.simulator.core.validation;

/**
 * Contratto dei validator di scenario: un validator per ogni singolo
 * scenario/operazione. Esegue TUTTI i controlli previsti e restituisce un
 * {@link ValidationResult} con ogni errore riscontrato.
 * <p>
 * Vale per gli scenari ordinari; gli scenari marcati con
 * {@code @ReplicatesOriginalBehavior} possono seguire una logica diversa
 * (es. fail-fast) per replicare il sistema originale.
 *
 * @param <T> tipo della request da validare
 */
public interface ScenarioValidator<T> {

    ValidationResult validate(T request);
}
