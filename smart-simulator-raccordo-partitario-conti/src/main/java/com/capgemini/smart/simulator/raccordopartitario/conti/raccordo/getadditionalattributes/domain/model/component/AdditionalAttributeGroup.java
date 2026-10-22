package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.component;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.AdditionalAttributeItem;
import java.util.List;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code AdditionalAttributes}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record AdditionalAttributeGroup(
        List<AdditionalAttributeItem> qualifiers,
        List<AdditionalAttributeItem> ancillaryData) {
}
