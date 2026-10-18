package com.capgemini.smart.simulator.documentviewer.document.application.port.out;

import com.capgemini.smart.simulator.documentviewer.document.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.document.domain.model.GetStaticDocumentSearchCriteria;

import java.util.Optional;

/**
 * Port in uscita: carica il documento statico corrispondente ai criteri di
 * ricerca. Restituisce un Optional vuoto se il documento non esiste.
 */
public interface LoadGetStaticDocumentPort {

    Optional<Document> getStaticDocument(GetStaticDocumentSearchCriteria criteria);
}
