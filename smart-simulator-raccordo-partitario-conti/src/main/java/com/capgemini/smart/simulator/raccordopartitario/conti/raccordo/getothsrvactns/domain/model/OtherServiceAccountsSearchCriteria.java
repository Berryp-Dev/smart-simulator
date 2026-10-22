package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountKey;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code GetOthSrvActnsDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record OtherServiceAccountsSearchCriteria(
        RequestHeader header,
        String caseID,
        ServiceAccountKey srvAcntKey) {
}
