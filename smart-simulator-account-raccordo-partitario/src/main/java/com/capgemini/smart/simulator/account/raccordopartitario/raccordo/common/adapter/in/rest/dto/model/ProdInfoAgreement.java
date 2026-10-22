package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO dello schema YAML {@code ProdInfoAgreement} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class ProdInfoAgreement {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String ty;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Getter(onMethod_ = @JsonProperty("sTy"))
    @Setter(onMethod_ = @JsonProperty("sTy"))
    private String sTy;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String key;
}
