package com.capgemini.smart.simulator.documentviewer.document.getstatic.application.service;

import com.capgemini.smart.simulator.core.exception.ValidationException;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.documentviewer.common.domain.exception.DocumentNotFoundException;
import com.capgemini.smart.simulator.documentviewer.document.getstatic.application.port.in.RetrieveGetStaticDocumentService;
import com.capgemini.smart.simulator.documentviewer.document.getstatic.application.port.out.LoadGetStaticDocumentPort;
import com.capgemini.smart.simulator.documentviewer.document.getstatic.application.validation.RetrieveGetStaticDocumentValidator;
import com.capgemini.smart.simulator.documentviewer.document.getstatic.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.document.getstatic.domain.model.GetStaticDocumentSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementazione del servizio getStatic: valida i criteri di ricerca e
 * carica il documento tramite la port in uscita; se la port non lo trova
 * solleva {@link DocumentNotFoundException}.
 */
@Service
public class RetrieveGetStaticDocumentServiceImpl implements RetrieveGetStaticDocumentService {

    @Autowired
    private RetrieveGetStaticDocumentValidator retrieveGetStaticDocumentValidator;
    @Autowired
    private LoadGetStaticDocumentPort loadGetStaticDocumentPort;

    @Override
    public Document getStaticDocument(GetStaticDocumentSearchCriteria criteria) {
        ValidationResult validationResult = retrieveGetStaticDocumentValidator.validate(criteria);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult);
        }
        return loadGetStaticDocumentPort.getStaticDocument(criteria)
                .orElseThrow(() -> new DocumentNotFoundException(criteria.productCode()));
    }
}
