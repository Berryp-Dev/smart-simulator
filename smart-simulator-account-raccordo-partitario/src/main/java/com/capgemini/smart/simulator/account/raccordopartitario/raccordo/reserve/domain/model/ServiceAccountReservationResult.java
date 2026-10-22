package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountExtendedKey;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code ReserveDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountReservationResult(
        ServiceAccountExtendedKey srvAcntExtKey) {
}
