package com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.dto.enums;

/**
 * Enum gemello lato DTO di
 * {@code domain.enums.PaperlessDocumentSignatureScenarioCode}: stesso nome
 * semplice, package separato. Il mapping verso l'enum di dominio avviene con
 * valueOf(name()) nel mapper (le costanti devono coincidere).
 */
public enum PaperlessDocumentSignatureScenarioCode {
    ALL_SIGNED,
    PARTIAL_SIGNATURE,
    SIGNATURE_ERROR
}
