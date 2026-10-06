package com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.PaperlessPracticeConfigureDtoReq;
import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.PaperlessPracticeConfigureDtoResp;
import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.enums.PaperlessPracticeConfigureScenarioCode;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeStatusCode;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfiguration;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfigureResult;

/**
 * Mapping tra il DTO di request/response e l'entita' di dominio Practice
 * Configure. scenarioCode e' convertito dall'enum gemello lato DTO
 * all'enum di dominio con uno switch esplicito (mai valueOf). Il mapper non
 * valida: un campo assente resta null nel dominio.
 */
public final class PaperlessPracticeConfigureMapper {

    private PaperlessPracticeConfigureMapper() {
    }

    public static PaperlessPracticeConfiguration mapToDomain(PaperlessPracticeConfigureDtoReq request) {
        com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode scenarioCode =
                mapToDomainScenarioCode(request.scenarioCode());
        return new PaperlessPracticeConfiguration(request.practiceIdOmnichannel(), request.customerDenomination(),
                scenarioCode, request.scope());
    }

    private static com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode
            mapToDomainScenarioCode(PaperlessPracticeConfigureScenarioCode dtoScenarioCode) {
        if (dtoScenarioCode == null) {
            return null;
        }
        return switch (dtoScenarioCode) {
            case SIGNATORIES_WAITING ->
                    com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode.SIGNATORIES_WAITING;
            case PRACTICE_LOCKED ->
                    com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode.PRACTICE_LOCKED;
            case PRACTICE_UNLOCKED ->
                    com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode.PRACTICE_UNLOCKED;
            case CONTEXT_SIGNATURE_WAITING ->
                    com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode.CONTEXT_SIGNATURE_WAITING;
            case CUSTOMER_SIGNATURE_WAITING ->
                    com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode.CUSTOMER_SIGNATURE_WAITING;
        };
    }

    public static PaperlessPracticeConfigureDtoResp mapToDtoResp(PaperlessPracticeConfigureResult result) {
        return new PaperlessPracticeConfigureDtoResp(mapToDtoStatus(result.paperlessStatus()));
    }

    private static String mapToDtoStatus(PaperlessPracticeStatusCode status) {
        return switch (status) {
            case ATTESA_FIRMATARI -> "ATTESA_FIRMATARI";
            case PRATICA_BLOCCATA -> "PRATICA_BLOCCATA";
            case PRATICA_SBLOCCATA -> "PRATICA_SBLOCCATA";
            case ATTESA_CONTESTO_FIRMA -> "ATTESA_CONTESTO_FIRMA";
            case ATTESA_FIRMA_CLIENTE -> "ATTESA_FIRMA_CLIENTE";
        };
    }
}
