package com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto;

import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.enums.PaperlessPracticeConfigureScenarioCode;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO dell'operazione POST /paperless/practice/configure.
 * <p>
 * scenarioCode e' tipizzato con l'enum gemello lato DTO (vedi
 * {@code adapter/in/rest/dto/enums}): un valore non riconosciuto fa fallire
 * la deserializzazione Jackson prima del validator, ma
 * {@code GlobalExceptionHandler} (core) intercetta questo caso specifico e
 * risponde comunque con un {@code ApiError}/{@code ValidationError} uniforme
 * (HTTP 400), coerente con lo standard di progetto.
 * <p>
 * I campi sono annotati con {@code @Schema(requiredMode = REQUIRED)} ai soli
 * fini della documentazione OpenAPI/Swagger (tutti obbligatori secondo
 * {@code PaperlessPracticeConfigureValidator}): l'annotazione non introduce
 * validazione Bean Validation ne' altera la deserializzazione.
 */
public record PaperlessPracticeConfigureDtoReq(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED) String practiceIdOmnichannel,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED) String customerDenomination,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED) PaperlessPracticeConfigureScenarioCode scenarioCode,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED) String scope) {
}
