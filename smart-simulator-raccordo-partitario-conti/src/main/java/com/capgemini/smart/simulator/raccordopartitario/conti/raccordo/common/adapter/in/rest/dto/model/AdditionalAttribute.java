package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code AdditionalAttribute} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class AdditionalAttribute {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String key;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;
}
