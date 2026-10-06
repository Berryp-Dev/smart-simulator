package com.capgemini.smart.simulator.paperless.practice.configure.domain.model;

import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeStatusCode;

/**
 * Esito di dominio dello scenario Practice Configure: nessuna dipendenza da
 * DTO ne' da concetti HTTP.
 */
public record PaperlessPracticeConfigureResult(PaperlessPracticeStatusCode paperlessStatus) {
}
