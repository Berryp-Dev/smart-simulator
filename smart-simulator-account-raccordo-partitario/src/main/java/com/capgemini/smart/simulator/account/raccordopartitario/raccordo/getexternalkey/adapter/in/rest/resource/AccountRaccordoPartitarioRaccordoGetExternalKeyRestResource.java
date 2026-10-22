package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoGetExternalKeyMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.application.port.in.RetrieveServiceAccountExternalKeyService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKeySearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API getExternalKey (POST /smartengine/raccordo/getexternalkey).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class RaccordoPartitarioContiRaccordoGetExternalKeyRestResource {

    @Autowired
    private RetrieveServiceAccountExternalKeyService retrieveServiceAccountExternalKeyService;

    @Operation(operationId = "getExternalKey")
    @PostMapping("/smartengine/raccordo/getexternalkey")
    public ResponseEntity<GetExternalKeyDtoResponse> getExternalKey(@RequestBody GetExternalKeyDtoRequest request) {
        ServiceAccountExternalKeySearchCriteria serviceAccountExternalKeySearchCriteria = RaccordoPartitarioContiRaccordoGetExternalKeyMapper.mapToDomain(request);
        ServiceAccountExternalKey result = retrieveServiceAccountExternalKeyService.getServiceAccountExternalKey(serviceAccountExternalKeySearchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(RaccordoPartitarioContiRaccordoGetExternalKeyMapper.mapToDtoResp(result));
    }
}
