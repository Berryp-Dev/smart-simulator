package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.ServiceAccountKey;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.domain.model.component.CurrentAccountServiceInfo;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code CurrentAccountsConfigureRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record CurrentAccountConfiguration(
        ServiceAccountKey srvAcntKey,
        CurrentAccountServiceInfo srvActnInfo) {
}
