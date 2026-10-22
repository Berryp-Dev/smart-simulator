package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.AdditionalAttributeItem;
import java.util.List;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code ActivateDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountActivationResult(
        List<AdditionalAttributeItem> newAncillaryData) {
}
