package com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.core.rest.request.RestRequest;
import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.PaperlessPracticeConfigureDtoReq;
import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.PaperlessPracticeConfigureDtoResp;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeStatusCode;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfiguration;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfigureResult;

/**
 * Mapping tra il DTO di request/response e l'entita' di dominio Practice
 * Configure. scenarioCode e' convertito dall'enum gemello lato DTO
 * all'enum di dominio con valueOf sul nome della costante (mai switch). Il mapper non
 * valida: un campo assente resta null nel dominio.
 */
public final class PaperlessPracticeConfigureMapper {

    private PaperlessPracticeConfigureMapper() {
    }

    public static PaperlessPracticeConfiguration mapToDomain(RestRequest<PaperlessPracticeConfigureDtoReq> request) {
        
        return new PaperlessPracticeConfiguration(request.getPayload().getPracticeIdOmnichannel(), request.getPayload().getCustomerDenomination(),
                PaperlessPracticeConfigureScenarioCode.valueOf(request.getPayload().getScenarioCode().name()), request.getPayload().getScope());
    }

    public static PaperlessPracticeConfigureDtoResp mapToDtoResp(PaperlessPracticeConfigureResult result) {
        return new PaperlessPracticeConfigureDtoResp(result.paperlessStatus().name());
    }

}
