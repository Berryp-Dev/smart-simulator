package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.ServiceAccountKey;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.StatusInformation;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code CreateDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountCreationResult(
        ServiceAccountKey srvAcntKey,
        StatusInformation statusInfo) {
}
