package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class ReserveServiceAccountServiceImplTest {

    private final ReserveServiceAccountServiceImpl service = new ReserveServiceAccountServiceImpl();

    @Test
    void reserveServiceAccount_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.reserveServiceAccount(null));

        assertTrue(exception.getMessage().contains("reserve"));
    }
}
