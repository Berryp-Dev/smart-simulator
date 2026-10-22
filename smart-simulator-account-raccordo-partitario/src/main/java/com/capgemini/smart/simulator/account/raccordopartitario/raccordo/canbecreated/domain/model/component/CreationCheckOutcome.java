package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.domain.model.component;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.AdditionalAttributeItem;
import java.util.List;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code CanBeCreatedResult}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record CreationCheckOutcome(
        ServiceAccountPreCreationData srvActnPreData,
        Boolean canBeCreated,
        String motivationCode,
        String motivationDescription,
        List<AdditionalAttributeItem> additionalInfo) {
}
