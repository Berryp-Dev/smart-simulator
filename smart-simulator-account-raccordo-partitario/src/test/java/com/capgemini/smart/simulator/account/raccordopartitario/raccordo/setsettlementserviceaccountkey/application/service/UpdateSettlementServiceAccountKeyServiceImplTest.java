package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception.ServiceNotImplementedException;
import org.junit.jupiter.api.Test;

class UpdateSettlementServiceAccountKeyServiceImplTest {

    private final UpdateSettlementServiceAccountKeyServiceImpl service = new UpdateSettlementServiceAccountKeyServiceImpl();

    @Test
    void updateSettlementServiceAccountKey_always_throwsServiceNotImplementedException() {
        ServiceNotImplementedException exception = assertThrows(ServiceNotImplementedException.class,
                () -> service.updateSettlementServiceAccountKey(null));

        assertTrue(exception.getMessage().contains("setSettlementServiceAccountKey"));
    }
}
