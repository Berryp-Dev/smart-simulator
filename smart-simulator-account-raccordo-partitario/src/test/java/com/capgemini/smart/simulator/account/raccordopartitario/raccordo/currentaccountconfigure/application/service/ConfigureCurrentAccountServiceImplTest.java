package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class ConfigureCurrentAccountServiceImplTest {

    private final ConfigureCurrentAccountServiceImpl service = new ConfigureCurrentAccountServiceImpl();

    @Test
    void configureCurrentAccount_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.configureCurrentAccount(null));

        assertTrue(exception.getMessage().contains("CurrentAccountConfigure"));
    }
}
