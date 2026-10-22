package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code RapportoCollegato} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class RapportoCollegato {

    private String relationshipCod;

    private String officeCod;

    private String accountKey;
}
