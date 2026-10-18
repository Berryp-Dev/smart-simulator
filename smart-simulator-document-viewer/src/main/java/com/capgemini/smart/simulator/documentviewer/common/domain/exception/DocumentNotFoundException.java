package com.capgemini.smart.simulator.documentviewer.common.domain.exception;

/**
 * Sollevata dal service quando nessun documento corrisponde ai criteri di
 * ricerca.
 */
public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(String productCode) {
        super("Documento non trovato per productCode: " + productCode);
    }
}
