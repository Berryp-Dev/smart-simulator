package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.AdditionalAttributeItem;
import java.util.List;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code ActivateDtoResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountActivationResult(
        List<AdditionalAttributeItem> newAncillaryData) {
}
