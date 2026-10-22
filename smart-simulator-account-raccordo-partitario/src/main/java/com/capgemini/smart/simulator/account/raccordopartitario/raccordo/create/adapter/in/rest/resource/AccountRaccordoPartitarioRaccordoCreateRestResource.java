package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.CreateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.CreateDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCreateMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.application.port.in.CreateServiceAccountService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.domain.model.ServiceAccountCreation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.domain.model.ServiceAccountCreationResult;
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
@Tag(name = "Account Raccordo Partitario / Raccordo")
@ReplicatesOriginalBehavior(system = "Account Raccordo Partitario", reference = "raccordopartitario_sales.yaml")
public class AccountRaccordoPartitarioRaccordoCreateRestResource {

    @Autowired
    private CreateServiceAccountService createServiceAccountService;

    @Operation(operationId = "create")
    @PostMapping("/smartengine/raccordo/create")
    public ResponseEntity<CreateDtoResponse> create(@RequestBody CreateDtoRequest request) {
        ServiceAccountCreation serviceAccountCreation = AccountRaccordoPartitarioRaccordoCreateMapper.mapToDomain(request);
        ServiceAccountCreationResult result = createServiceAccountService.createServiceAccount(serviceAccountCreation);
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountRaccordoPartitarioRaccordoCreateMapper.mapToDtoResp(result));
    }
}
