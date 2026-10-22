package com.capgemini.smart.simulator.raccordopartitario.conti.common.adapter.in.rest.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code ServiceErrorDto} (components-v1.2.2.yaml): dettaglio di un errore.
 * I campi null sono omessi dal JSON (tutti opzionali nello YAML).
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceErrorDto {

    private String errorCode;

    private String errorMessage;

    private String technicalDescription;
}
