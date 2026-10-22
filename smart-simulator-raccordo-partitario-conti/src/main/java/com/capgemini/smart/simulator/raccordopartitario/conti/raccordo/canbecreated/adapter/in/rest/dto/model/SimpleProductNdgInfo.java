package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.enums.SimpleProductNdgNature;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.NdgInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO dello schema YAML {@code SimpleProductNdgInfo} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SimpleProductNdgInfo extends NdgInfo {

    private SimpleProductNdgNature ndgNature;
}
