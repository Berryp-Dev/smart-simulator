package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountData;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code GetMainInfoDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountMainInfo(
        ServiceAccountData srvActnData) {
}
