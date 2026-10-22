package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.dto.CurrentAccountsConfigureRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCurrentAccountConfigureMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.application.port.in.ConfigureCurrentAccountService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.model.CurrentAccountConfiguration;
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
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales_currentaccounts_configure.yaml")
public class RaccordoPartitarioContiRaccordoCurrentAccountConfigureRestResource {

    @Autowired
    private ConfigureCurrentAccountService configureCurrentAccountService;

    @Operation(operationId = "CurrentAccountConfigure")
    @PostMapping("/ui-app/raccordo/sales/current-accounts/configure")
    public ResponseEntity<Void> currentAccountConfigure(@RequestBody CurrentAccountsConfigureRequest request) {
        CurrentAccountConfiguration currentAccountConfiguration = RaccordoPartitarioContiRaccordoCurrentAccountConfigureMapper.mapToDomain(request);
        configureCurrentAccountService.configureCurrentAccount(currentAccountConfiguration);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
