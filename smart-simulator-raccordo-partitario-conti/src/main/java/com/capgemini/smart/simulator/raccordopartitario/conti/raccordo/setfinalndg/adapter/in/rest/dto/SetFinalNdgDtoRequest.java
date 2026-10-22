package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.dto;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.Header;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.dto.model.SetFinalNdgDtoRequestPayload;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code SetFinalNdgDtoRequest} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class SetFinalNdgDtoRequest {

    private Header header;

    private SetFinalNdgDtoRequestPayload payload;
}
