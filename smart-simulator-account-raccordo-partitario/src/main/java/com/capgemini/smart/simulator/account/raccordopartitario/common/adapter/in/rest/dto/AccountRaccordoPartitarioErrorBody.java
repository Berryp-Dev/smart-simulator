package com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Busta di errore dichiarata dagli YAML (response ErrorResponse di
 * components-v1.2.2.yaml): {@code header} + {@code payload}. Il generatore non
 * produce un modello per questa busta (schema inline in una response), quindi
 * e' definita qui. L'header e' un {@link JsonNode} per riecheggiare quello
 * della richiesta senza dipendere dai modelli generati delle singole API.
 */
public record AccountRaccordoPartitarioErrorBody(JsonNode header, Payload payload) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Payload(ErrorItem mainError, List<ErrorItem> errorList) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record ErrorItem(String errorCode, String errorMessage, String technicalDescription) {
    }
}
