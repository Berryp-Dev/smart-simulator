package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code ProdInfoProdAttr}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ProductAttribute(
        String attrCod,
        String attrVal) {
}
