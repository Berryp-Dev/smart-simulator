package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code SrvActnData}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountData(
        String bankCod,
        String officeCod,
        String srvCod,
        String key,
        String externalKey,
        String legacyId,
        OffsetDateTime tsStart,
        OffsetDateTime tsEnd,
        ServiceAccountExtendedKey settlementSrvActn,
        StatusInformation statusInfo,
        NdgInformation ndgInfo,
        ProductInformation prodInfo,
        Map<String, Object> srvActnInfo) {
}
