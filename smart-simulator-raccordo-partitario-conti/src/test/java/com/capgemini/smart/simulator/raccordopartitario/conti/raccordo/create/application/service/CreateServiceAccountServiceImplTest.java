package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class CreateServiceAccountServiceImplTest {

    private final CreateServiceAccountServiceImpl service = new CreateServiceAccountServiceImpl();

    @Test
    void createServiceAccount_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.createServiceAccount(null));

        assertTrue(exception.getMessage().contains("create"));
    }
}
