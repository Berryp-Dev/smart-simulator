package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.domain.model.component;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code ExternalProdInfoProdAttr}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ExternalProductAttribute(
        String attrCod,
        String attrVal) {
}
