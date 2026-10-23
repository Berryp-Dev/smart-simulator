package com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model;

/**
 * Documento generato recuperato: id del documento (decriptato da bnlToken) e
 * contenuto binario (PDF).
 */
public record GeneratedDocument(String documentId, byte[] content) {
}
