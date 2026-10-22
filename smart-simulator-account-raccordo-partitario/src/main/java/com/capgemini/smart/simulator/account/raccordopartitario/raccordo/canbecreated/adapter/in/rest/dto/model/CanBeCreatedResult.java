package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.AdditionalAttribute;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code CanBeCreatedResult} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class CanBeCreatedResult {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private SimpleProduct srvActnPreData;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean canBeCreated;

    private String motivationCode;

    private String motivationDescription;

    private List<AdditionalAttribute> additionalInfo;
}
