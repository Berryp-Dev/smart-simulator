package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.domain.model.component;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code RapportoCollegato}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record LinkedAccountRelationship(
        String relationshipCod,
        String officeCod,
        String accountKey) {
}
