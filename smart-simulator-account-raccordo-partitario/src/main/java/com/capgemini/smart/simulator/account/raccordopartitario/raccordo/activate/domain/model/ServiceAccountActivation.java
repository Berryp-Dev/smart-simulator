package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountKey;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code ActivateDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountActivation(
        RequestHeader header,
        String caseID,
        ServiceAccountKey srvAcntKey) {
}
