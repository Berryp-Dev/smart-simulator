package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.Header;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.model.SetSettlementServiceAccountKeyDtoRequestPayload;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code SetSettlementServiceAccountKeyDtoRequest} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class SetSettlementServiceAccountKeyDtoRequest {

    private Header header;

    private SetSettlementServiceAccountKeyDtoRequestPayload payload;
}
