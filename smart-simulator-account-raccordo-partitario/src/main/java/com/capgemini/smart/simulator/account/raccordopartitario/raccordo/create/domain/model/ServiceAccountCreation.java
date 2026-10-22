package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountData;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code CreateDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountCreation(
        RequestHeader header,
        String caseID,
        ServiceAccountData srvActnData) {
}
