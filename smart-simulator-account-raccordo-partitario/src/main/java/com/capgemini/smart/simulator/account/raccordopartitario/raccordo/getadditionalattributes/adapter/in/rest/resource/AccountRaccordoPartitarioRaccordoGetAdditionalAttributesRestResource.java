package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.dto.GetAdditionalAttributesDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.dto.GetAdditionalAttributesDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoGetAdditionalAttributesMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.application.port.in.RetrieveServiceAccountAdditionalAttributesService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributes;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributesSearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API getAdditionalAttributes (POST /smartengine/raccordo/getadditionalattributes).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class RaccordoPartitarioContiRaccordoGetAdditionalAttributesRestResource {

    @Autowired
    private RetrieveServiceAccountAdditionalAttributesService retrieveServiceAccountAdditionalAttributesService;

    @Operation(operationId = "getAdditionalAttributes")
    @PostMapping("/smartengine/raccordo/getadditionalattributes")
    public ResponseEntity<GetAdditionalAttributesDtoResponse> getAdditionalAttributes(@RequestBody GetAdditionalAttributesDtoRequest request) {
        ServiceAccountAdditionalAttributesSearchCriteria serviceAccountAdditionalAttributesSearchCriteria = RaccordoPartitarioContiRaccordoGetAdditionalAttributesMapper.mapToDomain(request);
        ServiceAccountAdditionalAttributes result = retrieveServiceAccountAdditionalAttributesService.getServiceAccountAdditionalAttributes(serviceAccountAdditionalAttributesSearchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(RaccordoPartitarioContiRaccordoGetAdditionalAttributesMapper.mapToDtoResp(result));
    }
}
