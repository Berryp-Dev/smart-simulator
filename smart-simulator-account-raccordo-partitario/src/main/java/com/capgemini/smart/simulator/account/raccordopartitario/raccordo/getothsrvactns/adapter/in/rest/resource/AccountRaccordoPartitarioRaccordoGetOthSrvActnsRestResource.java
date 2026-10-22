package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.application.port.in.RetrieveOtherServiceAccountsService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.domain.model.OtherServiceAccounts;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.domain.model.OtherServiceAccountsSearchCriteria;
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
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class RaccordoPartitarioContiRaccordoGetOthSrvActnsRestResource {

    @Autowired
    private RetrieveOtherServiceAccountsService retrieveOtherServiceAccountsService;

    @Operation(operationId = "getOthSrvActns")
    @PostMapping("/smartengine/raccordo/getothsrvactns")
    public ResponseEntity<GetOthSrvActnsDtoResponse> getOthSrvActns(@RequestBody GetOthSrvActnsDtoRequest request) {
        OtherServiceAccountsSearchCriteria otherServiceAccountsSearchCriteria = RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper.mapToDomain(request);
        OtherServiceAccounts result = retrieveOtherServiceAccountsService.getOtherServiceAccounts(otherServiceAccountsSearchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper.mapToDtoResp(result));
    }
}
