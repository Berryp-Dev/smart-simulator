package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.dto;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model.Header;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.dto.model.GetExternalKeyDtoRequestPayload;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code GetExternalKeyDtoRequest} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class GetExternalKeyDtoRequest {

    private Header header;

    private GetExternalKeyDtoRequestPayload payload;
}
