package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.application.port.in.CancelServiceAccountService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.domain.model.ServiceAccountCancellation;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class CancelServiceAccountServiceImpl implements CancelServiceAccountService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: cancel";

    @Override
    public void cancelServiceAccount(ServiceAccountCancellation serviceAccountCancellation) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
