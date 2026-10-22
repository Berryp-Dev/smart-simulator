package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.CanBeCreatedRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.CanBeCreatedResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCanBeCreatedMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.application.port.in.CheckServiceAccountCreationService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.ServiceAccountCreationCheck;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.ServiceAccountCreationCheckResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API canBeCreated (POST /smartcatalog/raccordo/can-be-created).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Account Raccordo Partitario / Raccordo")
@ReplicatesOriginalBehavior(system = "Account Raccordo Partitario", reference = "raccordopartitario_sales.yaml")
public class AccountRaccordoPartitarioRaccordoCanBeCreatedRestResource {

    @Autowired
    private CheckServiceAccountCreationService checkServiceAccountCreationService;

    @Operation(operationId = "canBeCreated")
    @PostMapping("/smartcatalog/raccordo/can-be-created")
    public ResponseEntity<CanBeCreatedResponse> canBeCreated(@RequestBody CanBeCreatedRequest request) {
        ServiceAccountCreationCheck serviceAccountCreationCheck = AccountRaccordoPartitarioRaccordoCanBeCreatedMapper.mapToDomain(request);
        ServiceAccountCreationCheckResult result = checkServiceAccountCreationService.checkServiceAccountCreation(serviceAccountCreationCheck);
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountRaccordoPartitarioRaccordoCanBeCreatedMapper.mapToDtoResp(result));
    }
}
