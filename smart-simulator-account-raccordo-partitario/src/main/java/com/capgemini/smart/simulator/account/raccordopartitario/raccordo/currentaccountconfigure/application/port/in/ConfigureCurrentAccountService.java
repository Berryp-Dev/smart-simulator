package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.application.port.in;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.model.CurrentAccountConfiguration;

/**
 * Driving port dell'API CurrentAccountConfigure.
 */
public interface ConfigureCurrentAccountService {

    void configureCurrentAccount(CurrentAccountConfiguration currentAccountConfiguration);
}
