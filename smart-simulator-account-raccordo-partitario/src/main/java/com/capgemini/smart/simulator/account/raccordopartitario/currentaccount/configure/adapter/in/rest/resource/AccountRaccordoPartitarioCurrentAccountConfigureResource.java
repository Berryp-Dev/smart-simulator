package com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.resource;

import com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.exception.NotImplementedOperationException;
import com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.api.CurrentAccountApi;
import com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model.CurrentAccountsConfigureRequest;
import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementa l'interfaccia generata {@link CurrentAccountApi}: l'operazione
 * risponde HTTP 501 (non ancora implementata).
 */
@RestController
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti",
        reference = "raccordopartitario_sales_currentaccounts_configure.yaml")
public class AccountRaccordoPartitarioCurrentAccountConfigureResource implements CurrentAccountApi {

    @Override
    public ResponseEntity<Void> currentAccountConfigure(CurrentAccountsConfigureRequest currentAccountsConfigureRequest) {
        throw new NotImplementedOperationException(currentAccountsConfigureRequest);
    }
}
