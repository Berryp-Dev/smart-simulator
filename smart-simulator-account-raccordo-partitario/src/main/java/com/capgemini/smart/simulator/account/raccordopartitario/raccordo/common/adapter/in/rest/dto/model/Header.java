package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code Header} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class Header {

    private OffsetDateTime timestamp;

    private String trackId;

    private String applicationId;

    private String operationCode;

    private String channel;

    private String terminal;

    private String companyCode;

    private String branchCode;

    private String user;

    private String profile;

    private String logicalChannel;

    private String idempotencyId;

    private String transactionId;
}
