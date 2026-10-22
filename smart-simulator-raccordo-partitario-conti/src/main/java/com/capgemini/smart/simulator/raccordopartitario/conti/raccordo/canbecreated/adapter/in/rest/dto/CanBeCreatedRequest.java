package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.model.CanBeCreatedRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code CanBeCreatedRequest} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class CanBeCreatedRequest {

    private Header header;

    private CanBeCreatedRequestPayload payload;
}
