package com.capgemini.smart.simulator.paperless.document.signature.domain.model;

import com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode;

import java.util.List;

/**
 * Entita' di dominio radice dello scenario Document Signature: dati gia'
 * validati e convertiti (scenarioCode come enum di dominio).
 */
public record PaperlessDocumentSignature(String practiceIdOmnichannel, String scope,
                                         PaperlessDocumentSignatureScenarioCode scenarioCode,
                                         List<Long> documentIds) {
}
