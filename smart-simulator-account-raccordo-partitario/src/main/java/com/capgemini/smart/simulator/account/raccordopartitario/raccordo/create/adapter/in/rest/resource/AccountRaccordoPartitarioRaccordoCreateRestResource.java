package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.dto.CreateDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.dto.CreateDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCreateMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.application.port.in.CreateServiceAccountService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model.ServiceAccountCreation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model.ServiceAccountCreationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API create (POST /smartengine/raccordo/create).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class RaccordoPartitarioContiRaccordoCreateRestResource {

    @Autowired
    private CreateServiceAccountService createServiceAccountService;

    @Operation(operationId = "create")
    @PostMapping("/smartengine/raccordo/create")
    public ResponseEntity<CreateDtoResponse> create(@RequestBody CreateDtoRequest request) {
        ServiceAccountCreation serviceAccountCreation = RaccordoPartitarioContiRaccordoCreateMapper.mapToDomain(request);
        ServiceAccountCreationResult result = createServiceAccountService.createServiceAccount(serviceAccountCreation);
        return ResponseEntity.status(HttpStatus.CREATED).body(RaccordoPartitarioContiRaccordoCreateMapper.mapToDtoResp(result));
    }
}
