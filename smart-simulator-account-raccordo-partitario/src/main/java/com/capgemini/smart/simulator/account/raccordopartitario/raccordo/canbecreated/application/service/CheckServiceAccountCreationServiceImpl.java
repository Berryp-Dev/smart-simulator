package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.application.port.in.CheckServiceAccountCreationService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.domain.model.ServiceAccountCreationCheck;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.domain.model.ServiceAccountCreationCheckResult;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class CheckServiceAccountCreationServiceImpl implements CheckServiceAccountCreationService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: canBeCreated";

    @Override
    public ServiceAccountCreationCheckResult checkServiceAccountCreation(ServiceAccountCreationCheck serviceAccountCreationCheck) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
