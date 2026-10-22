package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.ServiceAccountExtendedKey;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code GetExternalKeyDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountExternalKey(
        ServiceAccountExtendedKey srvAcntExtKey) {
}
