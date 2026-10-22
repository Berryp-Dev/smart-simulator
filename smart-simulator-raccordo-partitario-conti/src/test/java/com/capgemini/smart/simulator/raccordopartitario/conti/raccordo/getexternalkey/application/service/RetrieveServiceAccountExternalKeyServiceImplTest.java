package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class RetrieveServiceAccountExternalKeyServiceImplTest {

    private final RetrieveServiceAccountExternalKeyServiceImpl service = new RetrieveServiceAccountExternalKeyServiceImpl();

    @Test
    void getServiceAccountExternalKey_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.getServiceAccountExternalKey(null));

        assertTrue(exception.getMessage().contains("getExternalKey"));
    }
}
