package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountKey;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code GetExternalKeyDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountExternalKeySearchCriteria(
        RequestHeader header,
        String caseID,
        ServiceAccountKey srvAcntKey) {
}
