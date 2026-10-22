package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.enums.ServiceAccountStatusCode;
import java.time.OffsetDateTime;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code StatusInfoRP}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record StatusInformation(
        OffsetDateTime tsStatus,
        ServiceAccountStatusCode status,
        String sStatus) {
}
