package com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.service;

import com.capgemini.smart.simulator.core.exception.ValidationException;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.documentviewer.common.domain.exception.DocumentNotFoundException;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.in.RetrieveGetGeneratedDocumentService;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.out.DecryptBnlTokenPort;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.out.LoadGeneratedDocumentPort;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.validation.RetrieveGetGeneratedDocumentValidator;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GeneratedDocument;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GetGeneratedDocumentCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementazione del servizio getGenerated: valida i criteri, decripta
 * bnlToken per ottenere l'id del documento, poi carica il documento tramite
 * la port in uscita; se la port non lo trova solleva
 * {@link DocumentNotFoundException}.
 */
@Service
public class RetrieveGetGeneratedDocumentServiceImpl implements RetrieveGetGeneratedDocumentService {

    @Autowired
    private RetrieveGetGeneratedDocumentValidator retrieveGetGeneratedDocumentValidator;
    @Autowired
    private DecryptBnlTokenPort decryptBnlTokenPort;
    @Autowired
    private LoadGeneratedDocumentPort loadGeneratedDocumentPort;

    @Override
    public GeneratedDocument getGeneratedDocument(GetGeneratedDocumentCriteria criteria) {
        ValidationResult validationResult = retrieveGetGeneratedDocumentValidator.validate(criteria);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult);
        }
        String documentId = decryptBnlTokenPort.decryptToken(criteria.bnlToken());
        return loadGeneratedDocumentPort.getGeneratedDocument(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(documentId));
    }
}
