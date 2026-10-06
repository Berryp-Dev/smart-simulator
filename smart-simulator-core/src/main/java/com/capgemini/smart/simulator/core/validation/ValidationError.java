package com.capgemini.smart.simulator.core.validation;

/**
 * Singolo errore di validazione: campo che ha violato il controllo e
 * messaggio descrittivo.
 */
public record ValidationError(String field, String message) {
}
