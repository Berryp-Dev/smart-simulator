package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component;

import java.util.List;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code ExternalProdInfo}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ExternalProductInformation(
        String prodExtKey,
        List<ExternalProductAttribute> prodAttrs) {
}
