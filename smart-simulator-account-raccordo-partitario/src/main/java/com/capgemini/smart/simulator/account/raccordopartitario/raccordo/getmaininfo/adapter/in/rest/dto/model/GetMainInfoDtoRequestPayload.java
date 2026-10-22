package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.SrvAcntKey;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code GetMainInfoDtoRequestPayload} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 * Nel dominio i suoi campi sono appiattiti nella radice della request.
 */
@Data
@NoArgsConstructor
public class GetMainInfoDtoRequestPayload {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String caseID;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private SrvAcntKey srvAcntKey;
}
