package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.Header;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.model.GetOthSrvActnsDtoRequestPayload;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code GetOthSrvActnsDtoRequest} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class GetOthSrvActnsDtoRequest {

    private Header header;

    private GetOthSrvActnsDtoRequestPayload payload;
}
