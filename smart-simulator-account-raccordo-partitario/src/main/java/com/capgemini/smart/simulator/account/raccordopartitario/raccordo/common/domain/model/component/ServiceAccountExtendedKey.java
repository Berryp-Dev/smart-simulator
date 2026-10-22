package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code SrvAcntExtKey}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountExtendedKey(
        String bankCod,
        String srvCod,
        String key,
        String officeCod) {
}
