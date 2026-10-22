package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component;

import java.util.List;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code ProdInfo}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ProductInformation(
        String prodKey,
        List<String> moduleKeys,
        List<ProductAgreement> agrmnts,
        List<String> prodExtKeys,
        List<ProductAttribute> prodAttrs) {
}
