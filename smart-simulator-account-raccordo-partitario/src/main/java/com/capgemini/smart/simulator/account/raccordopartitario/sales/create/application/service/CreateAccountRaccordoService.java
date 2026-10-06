package com.capgemini.smart.simulator.account.raccordopartitario.sales.create.application.service;

import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.application.port.in.CreateAccountRaccordoUseCase;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreation;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreationResult;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/** Implementa la creazione del rapporto: servizio senza stato e senza persistenza. */
@Service
public class CreateAccountRaccordoService implements CreateAccountRaccordoUseCase {

    private static final String STATUS_CREATED = "CREATED";
    private static final String STATUS_CREATED_DESCRIPTION = "Creato";

    @Override
    public AccountRaccordoCreationResult createAccountRaccordo(AccountRaccordoCreation creation) {
        return new AccountRaccordoCreationResult(
                creation.bankCode(),
                creation.serviceCode(),
                UUID.randomUUID().toString(),
                STATUS_CREATED,
                STATUS_CREATED_DESCRIPTION,
                Instant.now());
    }
}
