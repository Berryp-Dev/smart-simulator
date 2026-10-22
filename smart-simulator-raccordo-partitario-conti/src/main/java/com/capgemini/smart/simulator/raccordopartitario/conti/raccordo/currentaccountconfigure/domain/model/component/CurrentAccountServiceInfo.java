package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.model.component;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.enums.CurrencyCode;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code CurrentAccountsSrvActnInfo}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record CurrentAccountServiceInfo(
        String branchCode,
        String operationalProduct,
        String commercialProduct,
        CurrencyCode currency,
        String origination,
        String natureAndScopeCod,
        String riskCode,
        String offer,
        String agreement,
        List<LinkedAccountRelationship> linkedAccount,
        String linkedNdg,
        String linkedNdgRelationType,
        OffsetDateTime accountExpiryDate,
        String anatocismo,
        String settlementFrequency,
        OffsetDateTime settlementDate,
        String linkedCredit,
        Double firstDepositAmount,
        Boolean omitCheckbook) {
}
