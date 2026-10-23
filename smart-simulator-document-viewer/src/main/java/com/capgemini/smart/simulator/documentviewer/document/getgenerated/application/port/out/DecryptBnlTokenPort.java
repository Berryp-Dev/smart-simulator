package com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.out;

/**
 * Port in uscita: decripta bnlToken restituendo l'id del documento. Accetta e
 * restituisce solo tipi primitivi, nessuna dipendenza verso la libreria di
 * decriptazione (confinata nell'adapter).
 */
public interface DecryptBnlTokenPort {

    String decryptToken(String bnlToken);
}
