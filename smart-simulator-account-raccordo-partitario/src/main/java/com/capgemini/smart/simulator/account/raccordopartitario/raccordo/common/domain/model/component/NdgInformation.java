package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component;

import java.util.List;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code NdgInfo}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record NdgInformation(
        String cntrNdgKey,
        String cntrNdgType,
        List<String> cntrSignNdgKeys) {
}
