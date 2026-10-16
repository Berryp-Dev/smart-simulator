package com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.dto;

import java.util.List;

import com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.dto.enums.PaperlessDocumentSignatureScenarioCode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Request DTO dell'operazione POST /paperless/document/signature.
 */
@Data
public class PaperlessDocumentSignatureDtoReq{
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED) 
    String practiceIdOmnichannel;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED) 
    String scope;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED) 
    PaperlessDocumentSignatureScenarioCode scenarioCode;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED) 
    List<Long> listDocuments;
}
