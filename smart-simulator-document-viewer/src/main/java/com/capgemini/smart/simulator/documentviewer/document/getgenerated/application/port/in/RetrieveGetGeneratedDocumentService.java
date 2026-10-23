package com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.in;

import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GeneratedDocument;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GetGeneratedDocumentCriteria;

/**
 * Use case getGenerated: decripta bnlToken e recupera il documento generato
 * corrispondente.
 */
public interface RetrieveGetGeneratedDocumentService {

    GeneratedDocument getGeneratedDocument(GetGeneratedDocumentCriteria criteria);
}
