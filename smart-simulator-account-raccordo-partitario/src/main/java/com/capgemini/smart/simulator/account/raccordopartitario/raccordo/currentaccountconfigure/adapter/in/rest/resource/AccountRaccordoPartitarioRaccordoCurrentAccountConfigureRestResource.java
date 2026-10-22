package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.dto.CurrentAccountsConfigureRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.application.port.in.ConfigureCurrentAccountService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.domain.model.CurrentAccountConfiguration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API CurrentAccountConfigure (POST /ui-app/raccordo/sales/current-accounts/configure).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales_currentaccounts_configure.yaml.
 */
@RestController
@Tag(name = "Account Raccordo Partitario / Raccordo")
@ReplicatesOriginalBehavior(system = "Account Raccordo Partitario", reference = "raccordopartitario_sales_currentaccounts_configure.yaml")
public class AccountRaccordoPartitarioRaccordoCurrentAccountConfigureRestResource {

    @Autowired
    private ConfigureCurrentAccountService configureCurrentAccountService;

    @Operation(operationId = "CurrentAccountConfigure")
    @PostMapping("/ui-app/raccordo/sales/current-accounts/configure")
    public ResponseEntity<Void> currentAccountConfigure(@RequestBody CurrentAccountsConfigureRequest request) {
        CurrentAccountConfiguration currentAccountConfiguration = AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapper.mapToDomain(request);
        configureCurrentAccountService.configureCurrentAccount(currentAccountConfiguration);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
