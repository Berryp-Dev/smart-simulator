package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.adapter.in.rest.dto.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.dto.model.AdditionalAttribute;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code AdditionalAttributes} (contratto REPLICA ORIGINALE: nome e campi come da YAML).
 */
@Data
@NoArgsConstructor
public class AdditionalAttributes {

    private List<AdditionalAttribute> qualifiers;

    private List<AdditionalAttribute> ancillaryData;
}
