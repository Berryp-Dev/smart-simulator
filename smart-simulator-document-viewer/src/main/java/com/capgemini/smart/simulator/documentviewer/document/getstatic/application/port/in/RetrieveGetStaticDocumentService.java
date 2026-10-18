package com.capgemini.smart.simulator.documentviewer.document.getstatic.application.port.in;

import com.capgemini.smart.simulator.documentviewer.document.getstatic.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.document.getstatic.domain.model.GetStaticDocumentSearchCriteria;

/**
 * Use case getStatic: recupera il documento statico corrispondente ai
 * criteri di ricerca.
 */
public interface RetrieveGetStaticDocumentService {

    Document getStaticDocument(GetStaticDocumentSearchCriteria criteria);
}
