package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class RetrieveOtherServiceAccountsServiceImplTest {

    private final RetrieveOtherServiceAccountsServiceImpl service = new RetrieveOtherServiceAccountsServiceImpl();

    @Test
    void getOtherServiceAccounts_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.getOtherServiceAccounts(null));

        assertTrue(exception.getMessage().contains("getOthSrvActns"));
    }
}
