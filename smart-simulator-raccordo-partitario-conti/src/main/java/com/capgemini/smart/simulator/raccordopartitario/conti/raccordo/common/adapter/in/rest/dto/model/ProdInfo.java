package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code ProdInfo} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class ProdInfo {

    private String prodKey;

    private List<String> moduleKeys;

    private List<ProdInfoAgreement> agrmnts;

    private List<String> prodExtKeys;

    private List<ProdInfoProdAttr> prodAttrs;
}
