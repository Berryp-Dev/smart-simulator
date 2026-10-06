package com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.dto.PaperlessDocumentSignatureDtoReq;
import com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.dto.enums.PaperlessDocumentSignatureScenarioCode;
import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentSignature;

/**
 * Mapping tra il DTO di request e l'entita' di dominio Document Signature.
 * scenarioCode e' convertito dall'enum gemello lato DTO all'enum di dominio
 * con uno switch esplicito (mai valueOf). Il mapper non valida: un campo
 * assente resta null nel dominio.
 */
public final class PaperlessDocumentSignatureMapper {

    private PaperlessDocumentSignatureMapper() {
    }

    public static PaperlessDocumentSignature mapToDomain(PaperlessDocumentSignatureDtoReq request) {
        com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode scenarioCode =
                mapToDomainScenarioCode(request.scenarioCode());
        return new PaperlessDocumentSignature(request.practiceIdOmnichannel(), request.scope(), scenarioCode,
                request.listDocuments());
    }

    private static com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode
            mapToDomainScenarioCode(PaperlessDocumentSignatureScenarioCode dtoScenarioCode) {
        if (dtoScenarioCode == null) {
            return null;
        }
        return switch (dtoScenarioCode) {
            case ALL_SIGNED ->
                    com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode.ALL_SIGNED;
            case PARTIAL_SIGNATURE ->
                    com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode.PARTIAL_SIGNATURE;
            case SIGNATURE_ERROR ->
                    com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode.SIGNATURE_ERROR;
        };
    }
}
