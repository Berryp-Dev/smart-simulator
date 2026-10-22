package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.dto.ReserveDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.dto.ReserveDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoReserveMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.application.port.in.ReserveServiceAccountService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.domain.model.ServiceAccountReservation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.domain.model.ServiceAccountReservationResult;
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
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class RaccordoPartitarioContiRaccordoReserveRestResource {

    @Autowired
    private ReserveServiceAccountService reserveServiceAccountService;

    @Operation(operationId = "reserve")
    @PostMapping("/smartengine/raccordo/reserve")
    public ResponseEntity<ReserveDtoResponse> reserve(@RequestBody ReserveDtoRequest request) {
        ServiceAccountReservation serviceAccountReservation = RaccordoPartitarioContiRaccordoReserveMapper.mapToDomain(request);
        ServiceAccountReservationResult result = reserveServiceAccountService.reserveServiceAccount(serviceAccountReservation);
        return ResponseEntity.status(HttpStatus.OK).body(RaccordoPartitarioContiRaccordoReserveMapper.mapToDtoResp(result));
    }
}
