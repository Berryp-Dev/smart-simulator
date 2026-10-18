package com.capgemini.smart.simulator.documentviewer.document.domain.model;

/**
 * Documento statico recuperato: codice prodotto e contenuto binario (PDF).
 */
public record Document(String productCode, byte[] content) {
}
