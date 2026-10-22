package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class RetrieveServiceAccountAdditionalAttributesServiceImplTest {

    private final RetrieveServiceAccountAdditionalAttributesServiceImpl service = new RetrieveServiceAccountAdditionalAttributesServiceImpl();

    @Test
    void getServiceAccountAdditionalAttributes_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.getServiceAccountAdditionalAttributes(null));

        assertTrue(exception.getMessage().contains("getAdditionalAttributes"));
    }
}
