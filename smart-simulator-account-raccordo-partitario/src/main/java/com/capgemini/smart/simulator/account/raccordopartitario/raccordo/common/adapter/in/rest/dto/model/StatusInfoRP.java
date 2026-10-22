package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.enums.StatusInfoRPStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO dello schema YAML {@code StatusInfoRP} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class StatusInfoRP {

    private OffsetDateTime tsStatus;

    private StatusInfoRPStatus status;

    @Getter(onMethod_ = @JsonProperty("sStatus"))
    @Setter(onMethod_ = @JsonProperty("sStatus"))
    private String sStatus;
}
