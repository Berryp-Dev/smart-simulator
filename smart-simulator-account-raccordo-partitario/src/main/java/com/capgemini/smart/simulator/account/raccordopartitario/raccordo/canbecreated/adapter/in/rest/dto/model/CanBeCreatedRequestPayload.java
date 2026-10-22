package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model.SrvAcntExtKey;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code CanBeCreatedRequestPayload} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 * Nel dominio i suoi campi sono appiattiti nella radice della request.
 */
@Data
@NoArgsConstructor
public class CanBeCreatedRequestPayload {

    private Map<String, Object> additionalUserData;

    private SrvAcntExtKey settlementSrvActn;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private List<SimpleProduct> srvActnPreDatas;
}
