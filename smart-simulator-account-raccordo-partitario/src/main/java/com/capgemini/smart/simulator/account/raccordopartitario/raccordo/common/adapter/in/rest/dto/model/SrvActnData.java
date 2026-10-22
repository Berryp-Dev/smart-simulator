package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code SrvActnData} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class SrvActnData {

    private String bankCod;

    private String officeCod;

    private String srvCod;

    private String key;

    private String externalKey;

    private String legacyId;

    private OffsetDateTime tsStart;

    private OffsetDateTime tsEnd;

    private SrvAcntExtKey settlementSrvActn;

    private StatusInfoRP statusInfo;

    private NdgInfo ndgInfo;

    private ProdInfo prodInfo;

    private Map<String, Object> srvActnInfo;
}
