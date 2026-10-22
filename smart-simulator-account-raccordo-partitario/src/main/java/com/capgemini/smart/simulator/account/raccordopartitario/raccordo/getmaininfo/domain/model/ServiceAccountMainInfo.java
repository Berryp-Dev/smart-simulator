package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.ServiceAccountData;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code GetMainInfoDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountMainInfo(
        ServiceAccountData srvActnData) {
}
