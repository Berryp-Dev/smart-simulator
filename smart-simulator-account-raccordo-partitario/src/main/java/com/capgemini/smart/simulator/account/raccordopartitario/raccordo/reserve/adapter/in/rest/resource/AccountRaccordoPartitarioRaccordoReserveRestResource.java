package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.dto.ReserveDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.dto.ReserveDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoReserveMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.application.port.in.ReserveServiceAccountService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.domain.model.ServiceAccountReservation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.domain.model.ServiceAccountReservationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API reserve (POST /smartengine/raccordo/reserve).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Account Raccordo Partitario / Raccordo")
@ReplicatesOriginalBehavior(system = "Account Raccordo Partitario", reference = "raccordopartitario_sales.yaml")
public class AccountRaccordoPartitarioRaccordoReserveRestResource {

    @Autowired
    private ReserveServiceAccountService reserveServiceAccountService;

    @Operation(operationId = "reserve")
    @PostMapping("/smartengine/raccordo/reserve")
    public ResponseEntity<ReserveDtoResponse> reserve(@RequestBody ReserveDtoRequest request) {
        ServiceAccountReservation serviceAccountReservation = AccountRaccordoPartitarioRaccordoReserveMapper.mapToDomain(request);
        ServiceAccountReservationResult result = reserveServiceAccountService.reserveServiceAccount(serviceAccountReservation);
        return ResponseEntity.status(HttpStatus.OK).body(AccountRaccordoPartitarioRaccordoReserveMapper.mapToDtoResp(result));
    }
}
