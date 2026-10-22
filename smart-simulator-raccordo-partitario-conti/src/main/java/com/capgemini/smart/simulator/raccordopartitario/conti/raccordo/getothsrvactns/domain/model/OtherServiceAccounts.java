package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountData;
import java.util.List;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code GetOthSrvActnsDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record OtherServiceAccounts(
        List<ServiceAccountData> srvActnData) {
}
