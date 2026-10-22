package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code SimpleProduct}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountPreCreationData(
        String bankCod,
        String srvCod,
        String officeCod,
        PreCreationNdgInformation ndgInfo,
        ExternalProductInformation externalProdInfo) {
}
