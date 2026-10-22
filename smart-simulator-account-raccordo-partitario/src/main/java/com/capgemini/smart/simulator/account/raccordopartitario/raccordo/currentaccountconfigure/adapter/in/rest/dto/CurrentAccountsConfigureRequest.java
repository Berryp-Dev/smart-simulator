package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.dto;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model.SrvAcntKey;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.dto.model.CurrentAccountsSrvActnInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code CurrentAccountsConfigureRequest} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class CurrentAccountsConfigureRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private SrvAcntKey srvAcntKey;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private CurrentAccountsSrvActnInfo srvActnInfo;
}
