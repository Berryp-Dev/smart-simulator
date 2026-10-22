package com.capgemini.smart.simulator.raccordopartitario.conti.common.adapter.in.rest.dto;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.adapter.in.rest.dto.model.ServiceErrorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO dello schema YAML {@code ErrorResponse} (components-v1.2.2.yaml), body delle risposte
 * di errore come Error501. I campi null sono omessi dal JSON.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private ServiceErrorDto mainError;

    private List<ServiceErrorDto> errorList;
}
