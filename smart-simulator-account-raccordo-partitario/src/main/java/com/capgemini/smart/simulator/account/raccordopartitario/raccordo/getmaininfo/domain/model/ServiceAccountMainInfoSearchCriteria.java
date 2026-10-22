package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountKey;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code GetMainInfoDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountMainInfoSearchCriteria(
        RequestHeader header,
        String caseID,
        ServiceAccountKey srvAcntKey) {
}
