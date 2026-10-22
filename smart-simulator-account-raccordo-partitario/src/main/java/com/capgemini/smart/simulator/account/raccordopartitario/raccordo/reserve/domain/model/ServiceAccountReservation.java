package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountKey;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code ReserveDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountReservation(
        RequestHeader header,
        String caseID,
        ServiceAccountKey srvAcntKey) {
}
