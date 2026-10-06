package com.capgemini.smart.simulator.paperless.practice.configure.domain.model;

import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode;

/**
 * Entita' di dominio radice dello scenario Practice Configure: dati gia'
 * validati e convertiti (scenarioCode come enum di dominio).
 */
public record PaperlessPracticeConfiguration(String practiceIdOmnichannel, String customerDenomination,
                                               PaperlessPracticeConfigureScenarioCode scenarioCode, String scope) {
}
