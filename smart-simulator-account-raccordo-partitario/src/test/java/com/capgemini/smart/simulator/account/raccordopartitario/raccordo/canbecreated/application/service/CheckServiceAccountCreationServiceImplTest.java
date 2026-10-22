package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class CheckServiceAccountCreationServiceImplTest {

    private final CheckServiceAccountCreationServiceImpl service = new CheckServiceAccountCreationServiceImpl();

    @Test
    void checkServiceAccountCreation_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.checkServiceAccountCreation(null));

        assertTrue(exception.getMessage().contains("canBeCreated"));
    }
}
