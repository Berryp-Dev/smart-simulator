package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component.CreationCheckOutcome;
import java.util.List;

/**
 * Modello di dominio (response) corrispondente allo schema YAML {@code CanBeCreatedResponse}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountCreationCheckResult(
        List<CreationCheckOutcome> results) {
}
