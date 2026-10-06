package com.capgemini.smart.simulator.paperless.practice.configure.domain.model;

import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeStatusCode;

/**
 * Aggiornamento di stato della pratica da pubblicare verso l'esterno.
 * lockReason e' nullable: valorizzato solo per la pratica bloccata.
 */
public record PaperlessPracticeStatusUpdate(String practiceId, String processId, String externalId,
                                            String practiceScope, String lockReason,
                                            PaperlessPracticeStatusCode status) {
}
