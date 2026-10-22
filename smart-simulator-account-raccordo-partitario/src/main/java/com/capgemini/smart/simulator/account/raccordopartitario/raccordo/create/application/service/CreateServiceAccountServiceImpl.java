package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.application.port.in.CreateServiceAccountService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model.ServiceAccountCreation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model.ServiceAccountCreationResult;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class CreateServiceAccountServiceImpl implements CreateServiceAccountService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: create";

    @Override
    public ServiceAccountCreationResult createServiceAccount(ServiceAccountCreation serviceAccountCreation) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
