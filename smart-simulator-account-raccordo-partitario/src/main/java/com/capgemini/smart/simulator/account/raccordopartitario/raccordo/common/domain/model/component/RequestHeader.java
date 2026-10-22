package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component;

import java.time.OffsetDateTime;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code Header}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record RequestHeader(
        OffsetDateTime timestamp,
        String trackId,
        String applicationId,
        String operationCode,
        String channel,
        String terminal,
        String companyCode,
        String branchCode,
        String user,
        String profile,
        String logicalChannel,
        String idempotencyId,
        String transactionId) {
}
