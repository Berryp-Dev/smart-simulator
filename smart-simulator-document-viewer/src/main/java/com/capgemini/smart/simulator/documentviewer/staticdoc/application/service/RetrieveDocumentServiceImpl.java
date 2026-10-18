package com.capgemini.smart.simulator.documentviewer.staticdoc.application.service;

import com.capgemini.smart.simulator.core.exception.ValidationException;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.documentviewer.staticdoc.application.port.in.RetrieveDocumentService;
import com.capgemini.smart.simulator.documentviewer.staticdoc.application.port.out.LoadDocumentPort;
import com.capgemini.smart.simulator.documentviewer.staticdoc.application.validation.RetrieveDocumentValidator;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.exception.DocumentNotFoundException;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementazione dello scenario Static Doc (retrieve): valida i criteri di
 * ricerca e carica il documento tramite la port in uscita; se la port non
 * lo trova solleva {@link DocumentNotFoundException}.
 */
@Service
public class RetrieveDocumentServiceImpl implements RetrieveDocumentService {

    @Autowired
    private RetrieveDocumentValidator retrieveDocumentValidator;
    @Autowired
    private LoadDocumentPort loadDocumentPort;

    @Override
    public Document getDocument(DocumentSearchCriteria criteria) {
        ValidationResult validationResult = retrieveDocumentValidator.validate(criteria);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult);
        }
        return loadDocumentPort.getDocument(criteria)
                .orElseThrow(() -> new DocumentNotFoundException(criteria.productCode()));
    }
}
