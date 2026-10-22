package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.adapter.in.rest.dto.CancelDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCancelMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.application.port.in.CancelServiceAccountService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.domain.model.ServiceAccountCancellation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API cancel (POST /smartengine/raccordo/cancel).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Account Raccordo Partitario / Raccordo")
@ReplicatesOriginalBehavior(system = "Account Raccordo Partitario", reference = "raccordopartitario_sales.yaml")
public class AccountRaccordoPartitarioRaccordoCancelRestResource {

    @Autowired
    private CancelServiceAccountService cancelServiceAccountService;

    @Operation(operationId = "cancel")
    @PostMapping("/smartengine/raccordo/cancel")
    public ResponseEntity<Void> cancel(@RequestBody CancelDtoRequest request) {
        ServiceAccountCancellation serviceAccountCancellation = AccountRaccordoPartitarioRaccordoCancelMapper.mapToDomain(request);
        cancelServiceAccountService.cancelServiceAccount(serviceAccountCancellation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
