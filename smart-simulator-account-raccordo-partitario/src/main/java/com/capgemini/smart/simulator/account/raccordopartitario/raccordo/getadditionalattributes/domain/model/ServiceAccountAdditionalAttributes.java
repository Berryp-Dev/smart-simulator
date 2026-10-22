package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.component.AdditionalAttributeGroup;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code GetAdditionalAttributesDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountAdditionalAttributes(
        AdditionalAttributeGroup accountAdditionalAttributes,
        AdditionalAttributeGroup contractAdditionalAttributes) {
}
