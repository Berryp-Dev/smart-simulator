package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code ExternalProdInfo} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class ExternalProdInfo {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String prodExtKey;

    private List<ExternalProdInfoProdAttr> prodAttrs;
}
