package com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.core.rest.request.RestRequest;
import com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.dto.PaperlessDocumentSignatureDtoReq;
import com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode;
import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentSignature;

/**
 * Mapping tra il DTO di request e l'entita' di dominio Document Signature.
 * scenarioCode e' convertito dall'enum gemello lato DTO all'enum di dominio
 * con valueOf sul nome della costante (mai switch). Il mapper non valida: un campo
 * assente resta null nel dominio.
 */
public final class PaperlessDocumentSignatureMapper {

    private PaperlessDocumentSignatureMapper() {
    }

    public static PaperlessDocumentSignature mapToDomain(RestRequest<PaperlessDocumentSignatureDtoReq> request) {
        return new PaperlessDocumentSignature(request.getPayload().getPracticeIdOmnichannel(), request.getPayload().getScope(), PaperlessDocumentSignatureScenarioCode.valueOf(request.getPayload().getScenarioCode().name()) ,
                request.getPayload().getListDocuments());
    }
    
}
