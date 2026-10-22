package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class RetrieveServiceAccountMainInfoServiceImplTest {

    private final RetrieveServiceAccountMainInfoServiceImpl service = new RetrieveServiceAccountMainInfoServiceImpl();

    @Test
    void getServiceAccountMainInfo_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.getServiceAccountMainInfo(null));

        assertTrue(exception.getMessage().contains("getMainInfo"));
    }
}
