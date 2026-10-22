package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code NdgInfo} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class NdgInfo {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String cntrNdgKey;

    private String cntrNdgType;

    private List<String> cntrSignNdgKeys;
}
