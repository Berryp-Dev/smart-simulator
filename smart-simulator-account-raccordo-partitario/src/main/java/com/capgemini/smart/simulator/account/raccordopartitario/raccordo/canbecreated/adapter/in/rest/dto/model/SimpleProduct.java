package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code SimpleProduct} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class SimpleProduct {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String bankCod;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String srvCod;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String officeCod;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private SimpleProductNdgInfo ndgInfo;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private ExternalProdInfo externalProdInfo;
}
