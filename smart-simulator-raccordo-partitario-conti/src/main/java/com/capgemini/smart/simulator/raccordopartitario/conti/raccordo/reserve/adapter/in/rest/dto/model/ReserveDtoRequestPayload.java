package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.SrvAcntKey;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code ReserveDtoRequestPayload} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 * Nel dominio i suoi campi sono appiattiti nella radice della request.
 */
@Data
@NoArgsConstructor
public class ReserveDtoRequestPayload {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String caseID;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private SrvAcntKey srvAcntKey;
}
