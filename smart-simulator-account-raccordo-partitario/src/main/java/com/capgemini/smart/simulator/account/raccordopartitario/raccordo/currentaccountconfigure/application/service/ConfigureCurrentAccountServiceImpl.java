package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.application.port.in.ConfigureCurrentAccountService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.model.CurrentAccountConfiguration;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class ConfigureCurrentAccountServiceImpl implements ConfigureCurrentAccountService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: CurrentAccountConfigure";

    @Override
    public void configureCurrentAccount(CurrentAccountConfiguration currentAccountConfiguration) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
