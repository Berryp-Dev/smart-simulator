package com.capgemini.smart.simulator.documentviewer.document.getstatic.domain.model;

/**
 * Documento statico recuperato: codice prodotto e contenuto binario (PDF).
 */
public record Document(String productCode, byte[] content) {
}
