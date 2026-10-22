package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class CancelServiceAccountServiceImplTest {

    private final CancelServiceAccountServiceImpl service = new CancelServiceAccountServiceImpl();

    @Test
    void cancelServiceAccount_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.cancelServiceAccount(null));

        assertTrue(exception.getMessage().contains("cancel"));
    }
}
