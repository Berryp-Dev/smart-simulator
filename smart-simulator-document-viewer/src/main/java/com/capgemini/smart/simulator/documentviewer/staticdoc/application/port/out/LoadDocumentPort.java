package com.capgemini.smart.simulator.documentviewer.staticdoc.application.port.out;

import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;

import java.util.Optional;

/**
 * Port in uscita: carica il documento corrispondente ai criteri di ricerca.
 * Restituisce un Optional vuoto se il documento non esiste.
 */
public interface LoadDocumentPort {

    Optional<Document> getDocument(DocumentSearchCriteria criteria);
}
