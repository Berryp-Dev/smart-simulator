package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.SrvActnData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code GetOthSrvActnsDtoResponse} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class GetOthSrvActnsDtoResponse {

    private List<SrvActnData> srvActnData;
}
