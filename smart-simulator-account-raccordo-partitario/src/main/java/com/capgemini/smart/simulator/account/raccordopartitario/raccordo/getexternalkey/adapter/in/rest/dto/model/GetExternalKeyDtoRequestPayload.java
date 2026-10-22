package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model.SrvAcntKey;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code GetExternalKeyDtoRequestPayload} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 * Nel dominio i suoi campi sono appiattiti nella radice della request.
 */
@Data
@NoArgsConstructor
public class GetExternalKeyDtoRequestPayload {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String caseID;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private SrvAcntKey srvAcntKey;
}
