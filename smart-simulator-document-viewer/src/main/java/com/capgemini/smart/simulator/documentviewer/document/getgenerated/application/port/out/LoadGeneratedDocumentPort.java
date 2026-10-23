package com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.out;

import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GeneratedDocument;

import java.util.Optional;

/**
 * Port in uscita: carica il documento generato corrispondente all'id
 * (decriptato da bnlToken). Restituisce un Optional vuoto se il documento non
 * esiste.
 */
public interface LoadGeneratedDocumentPort {

    Optional<GeneratedDocument> getGeneratedDocument(String documentId);
}
