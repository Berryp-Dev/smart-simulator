package com.capgemini.smart.simulator.account.raccordopartitario.sales.create.application.service;

import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreation;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreationResult;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateAccountRaccordoServiceTest {

    private final CreateAccountRaccordoService service = new CreateAccountRaccordoService();

    @Test
    void createReturnsCreatedStatusAndEchoesCodes() {
        Instant before = Instant.now();

        AccountRaccordoCreationResult result = service.createAccountRaccordo(new AccountRaccordoCreation("01005", "SRV1"));

        assertEquals("01005", result.bankCode());
        assertEquals("SRV1", result.serviceCode());
        assertEquals("CREATED", result.status());
        assertEquals("Creato", result.statusDescription());
        assertNotNull(result.statusTimestamp());
        assertFalse(result.statusTimestamp().isBefore(before));
    }

    @Test
    void createGeneratesValidUuidDifferentOnEachCall() {
        AccountRaccordoCreation creation = new AccountRaccordoCreation("01005", "SRV1");

        String first = service.createAccountRaccordo(creation).serviceAccountKey();
        String second = service.createAccountRaccordo(creation).serviceAccountKey();

        assertEquals(first, UUID.fromString(first).toString());
        assertEquals(second, UUID.fromString(second).toString());
        assertNotEquals(first, second);
    }

    @Test
    void createWithMissingCodesReportsThemMissing() {
        AccountRaccordoCreationResult result = service.createAccountRaccordo(new AccountRaccordoCreation(null, null));

        assertNull(result.bankCode());
        assertNull(result.serviceCode());
    }
}
