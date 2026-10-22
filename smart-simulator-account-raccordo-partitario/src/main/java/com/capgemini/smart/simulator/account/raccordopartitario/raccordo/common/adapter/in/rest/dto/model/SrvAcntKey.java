package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code SrvAcntKey} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class SrvAcntKey {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String bankCod;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String srvCod;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String key;
}
