package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.dto.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code SrvActnInfo} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class SrvActnInfo {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String branchCode;

    private String operationalProduct;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String commercialProduct;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Currency currency;

    private String origination;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String natureAndScopeCod;

    private String riskCode;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String offer;

    private String agreement;

    private List<RapportoCollegato> linkedAccount;

    private String linkedNdg;

    private String linkedNdgRelationType;

    private OffsetDateTime accountExpiryDate;

    private String anatocismo;

    private String settlementFrequency;

    private OffsetDateTime settlementDate;

    private String linkedCredit;

    private Double firstDepositAmount;

    private Boolean omitCheckbook;
}
