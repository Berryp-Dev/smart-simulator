package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class UpdateFinalNdgServiceImplTest {

    private final UpdateFinalNdgServiceImpl service = new UpdateFinalNdgServiceImpl();

    @Test
    void updateFinalNdg_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.updateFinalNdg(null));

        assertTrue(exception.getMessage().contains("setFinalNdg"));
    }
}
