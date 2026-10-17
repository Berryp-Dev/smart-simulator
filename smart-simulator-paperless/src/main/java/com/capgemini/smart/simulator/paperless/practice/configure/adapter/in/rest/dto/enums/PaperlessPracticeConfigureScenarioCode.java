package com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.enums;

/**
 * Enum gemello lato DTO di
 * {@code domain.enums.PaperlessPracticeConfigureScenarioCode}: stesso nome
 * semplice, package separato. Il mapping verso l'enum di dominio avviene con
 * valueOf(name()) nel mapper (le costanti devono coincidere).
 */
public enum PaperlessPracticeConfigureScenarioCode {
    SIGNATORIES_WAITING,
    PRACTICE_LOCKED,
    PRACTICE_UNLOCKED,
    CONTEXT_SIGNATURE_WAITING,
    CUSTOMER_SIGNATURE_WAITING
}
