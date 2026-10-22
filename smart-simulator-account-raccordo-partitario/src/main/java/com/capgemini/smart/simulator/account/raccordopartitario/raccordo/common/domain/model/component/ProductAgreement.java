package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code ProdInfoAgreement}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ProductAgreement(
        String ty,
        String sTy,
        String key) {
}
