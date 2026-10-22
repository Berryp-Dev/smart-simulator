package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoGetExternalKeyMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.application.port.in.RetrieveServiceAccountExternalKeyService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.domain.model.ServiceAccountExternalKey;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.domain.model.ServiceAccountExternalKeySearchCriteria;
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
@Tag(name = "Account Raccordo Partitario / Raccordo")
@ReplicatesOriginalBehavior(system = "Account Raccordo Partitario", reference = "raccordopartitario_sales.yaml")
public class AccountRaccordoPartitarioRaccordoGetExternalKeyRestResource {

    @Autowired
    private RetrieveServiceAccountExternalKeyService retrieveServiceAccountExternalKeyService;

    @Operation(operationId = "getExternalKey")
    @PostMapping("/smartengine/raccordo/getexternalkey")
    public ResponseEntity<GetExternalKeyDtoResponse> getExternalKey(@RequestBody GetExternalKeyDtoRequest request) {
        ServiceAccountExternalKeySearchCriteria serviceAccountExternalKeySearchCriteria = AccountRaccordoPartitarioRaccordoGetExternalKeyMapper.mapToDomain(request);
        ServiceAccountExternalKey result = retrieveServiceAccountExternalKeyService.getServiceAccountExternalKey(serviceAccountExternalKeySearchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(AccountRaccordoPartitarioRaccordoGetExternalKeyMapper.mapToDtoResp(result));
    }
}
