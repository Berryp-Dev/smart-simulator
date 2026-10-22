package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model.SrvActnData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code CreateDtoRequestPayload} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 * Nel dominio i suoi campi sono appiattiti nella radice della request.
 */
@Data
@NoArgsConstructor
public class CreateDtoRequestPayload {

    private String caseID;

    private SrvActnData srvActnData;
}
