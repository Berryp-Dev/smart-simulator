package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.application.service;

import com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.application.port.in.ActivateServiceAccountService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.domain.model.ServiceAccountActivation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.domain.model.ServiceAccountActivationResult;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class ActivateServiceAccountServiceImpl implements ActivateServiceAccountService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: activate";

    @Override
    public ServiceAccountActivationResult activateServiceAccount(ServiceAccountActivation serviceAccountActivation) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
