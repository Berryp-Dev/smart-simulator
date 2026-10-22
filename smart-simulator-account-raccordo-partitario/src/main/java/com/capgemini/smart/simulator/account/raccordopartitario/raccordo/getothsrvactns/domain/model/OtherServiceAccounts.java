package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.ServiceAccountData;
import java.util.List;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code GetOthSrvActnsDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record OtherServiceAccounts(
        List<ServiceAccountData> srvActnData) {
}
