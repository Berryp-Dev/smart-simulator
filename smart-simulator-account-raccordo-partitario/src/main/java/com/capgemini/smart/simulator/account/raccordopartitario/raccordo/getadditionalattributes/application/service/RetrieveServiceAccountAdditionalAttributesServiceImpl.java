package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.application.service;

import com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.application.port.in.RetrieveServiceAccountAdditionalAttributesService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributes;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributesSearchCriteria;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class RetrieveServiceAccountAdditionalAttributesServiceImpl implements RetrieveServiceAccountAdditionalAttributesService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: getAdditionalAttributes";

    @Override
    public ServiceAccountAdditionalAttributes getServiceAccountAdditionalAttributes(ServiceAccountAdditionalAttributesSearchCriteria serviceAccountAdditionalAttributesSearchCriteria) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
