package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class ActivateServiceAccountServiceImplTest {

    private final ActivateServiceAccountServiceImpl service = new ActivateServiceAccountServiceImpl();

    @Test
    void activateServiceAccount_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.activateServiceAccount(null));

        assertTrue(exception.getMessage().contains("activate"));
    }
}
