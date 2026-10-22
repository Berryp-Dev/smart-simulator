package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.domain.model.CurrentAccountConfiguration;

/**
 * Driving port dell'API CurrentAccountConfigure.
 */
public interface ConfigureCurrentAccountService {

    void configureCurrentAccount(CurrentAccountConfiguration currentAccountConfiguration);
}
