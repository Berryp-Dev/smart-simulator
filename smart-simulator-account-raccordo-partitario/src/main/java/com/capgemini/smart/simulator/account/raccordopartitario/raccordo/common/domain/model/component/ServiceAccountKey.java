package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code SrvAcntKey}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountKey(
        String bankCod,
        String srvCod,
        String key) {
}
