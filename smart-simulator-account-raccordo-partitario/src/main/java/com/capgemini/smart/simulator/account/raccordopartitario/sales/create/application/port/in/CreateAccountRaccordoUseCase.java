package com.capgemini.smart.simulator.account.raccordopartitario.sales.create.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreation;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreationResult;

/** Caso d'uso: creazione di un rapporto di raccordo partitario. */
public interface CreateAccountRaccordoUseCase {

    /**
     * Crea il rapporto: genera la chiave e restituisce lo stato iniziale.
     *
     * @param creation dati in ingresso
     * @return esito della creazione
     */
    AccountRaccordoCreationResult createAccountRaccordo(AccountRaccordoCreation creation);
}
