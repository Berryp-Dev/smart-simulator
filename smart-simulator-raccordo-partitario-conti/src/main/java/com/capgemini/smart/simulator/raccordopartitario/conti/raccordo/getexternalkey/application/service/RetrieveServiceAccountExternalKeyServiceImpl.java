package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.application.port.in.RetrieveServiceAccountExternalKeyService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKeySearchCriteria;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class RetrieveServiceAccountExternalKeyServiceImpl implements RetrieveServiceAccountExternalKeyService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: getExternalKey";

    @Override
    public ServiceAccountExternalKey getServiceAccountExternalKey(ServiceAccountExternalKeySearchCriteria serviceAccountExternalKeySearchCriteria) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
