package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.SetSettlementServiceAccountKeyDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.application.port.in.UpdateSettlementServiceAccountKeyService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.domain.model.SettlementServiceAccountKeyAssignment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API setSettlementServiceAccountKey (POST /smartengine/raccordo/setsettlementserviceaccountkey).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Account Raccordo Partitario / Raccordo")
@ReplicatesOriginalBehavior(system = "Account Raccordo Partitario", reference = "raccordopartitario_sales.yaml")
public class AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyRestResource {

    @Autowired
    private UpdateSettlementServiceAccountKeyService updateSettlementServiceAccountKeyService;

    @Operation(operationId = "setSettlementServiceAccountKey")
    @PostMapping("/smartengine/raccordo/setsettlementserviceaccountkey")
    public ResponseEntity<Void> setSettlementServiceAccountKey(@RequestBody SetSettlementServiceAccountKeyDtoRequest request) {
        SettlementServiceAccountKeyAssignment settlementServiceAccountKeyAssignment = AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyMapper.mapToDomain(request);
        updateSettlementServiceAccountKeyService.updateSettlementServiceAccountKey(settlementServiceAccountKeyAssignment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
