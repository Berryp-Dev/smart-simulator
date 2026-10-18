package com.capgemini.smart.simulator.documentviewer.staticdoc.application.port.in;

import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;

/**
 * Use case Static Doc (retrieve): recupera il documento corrispondente ai
 * criteri di ricerca.
 */
public interface RetrieveDocumentService {

    Document getDocument(DocumentSearchCriteria criteria);
}
