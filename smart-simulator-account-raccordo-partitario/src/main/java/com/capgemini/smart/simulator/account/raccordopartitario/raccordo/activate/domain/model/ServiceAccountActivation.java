package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.ServiceAccountKey;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code ActivateDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountActivation(
        RequestHeader header,
        String caseID,
        ServiceAccountKey srvAcntKey) {
}
