package com.capgemini.smart.simulator.paperless.document.signature.domain.model;

import com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureStatusCode;

/**
 * Aggiornamento di stato di un singolo documento da pubblicare verso l'esterno.
 */
public record PaperlessDocumentStatusUpdate(String practiceId, String externalId, String practiceScope,
                                            Long documentId, PaperlessDocumentSignatureStatusCode status) {
}
