package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoGetOthSrvActnsMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.application.port.in.RetrieveOtherServiceAccountsService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model.OtherServiceAccounts;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model.OtherServiceAccountsSearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API getOthSrvActns (POST /smartengine/raccordo/getothsrvactns).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Account Raccordo Partitario / Raccordo")
@ReplicatesOriginalBehavior(system = "Account Raccordo Partitario", reference = "raccordopartitario_sales.yaml")
public class AccountRaccordoPartitarioRaccordoGetOthSrvActnsRestResource {

    @Autowired
    private RetrieveOtherServiceAccountsService retrieveOtherServiceAccountsService;

    @Operation(operationId = "getOthSrvActns")
    @PostMapping("/smartengine/raccordo/getothsrvactns")
    public ResponseEntity<GetOthSrvActnsDtoResponse> getOthSrvActns(@RequestBody GetOthSrvActnsDtoRequest request) {
        OtherServiceAccountsSearchCriteria otherServiceAccountsSearchCriteria = AccountRaccordoPartitarioRaccordoGetOthSrvActnsMapper.mapToDomain(request);
        OtherServiceAccounts result = retrieveOtherServiceAccountsService.getOtherServiceAccounts(otherServiceAccountsSearchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(AccountRaccordoPartitarioRaccordoGetOthSrvActnsMapper.mapToDtoResp(result));
    }
}
