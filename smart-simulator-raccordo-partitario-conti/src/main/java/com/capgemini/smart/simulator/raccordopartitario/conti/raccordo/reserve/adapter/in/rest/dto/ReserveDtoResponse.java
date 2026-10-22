package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.dto;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.SrvAcntExtKey;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code ReserveDtoResponse} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class ReserveDtoResponse {

    private SrvAcntExtKey srvAcntExtKey;
}
