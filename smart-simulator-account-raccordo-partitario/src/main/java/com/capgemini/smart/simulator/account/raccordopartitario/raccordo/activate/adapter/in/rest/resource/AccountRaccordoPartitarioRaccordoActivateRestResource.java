package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.dto.ActivateDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.dto.ActivateDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoActivateMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.application.port.in.ActivateServiceAccountService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model.ServiceAccountActivation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model.ServiceAccountActivationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API activate (POST /smartengine/raccordo/activate).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class RaccordoPartitarioContiRaccordoActivateRestResource {

    @Autowired
    private ActivateServiceAccountService activateServiceAccountService;

    @Operation(operationId = "activate")
    @PostMapping("/smartengine/raccordo/activate")
    public ResponseEntity<ActivateDtoResponse> activate(@RequestBody ActivateDtoRequest request) {
        ServiceAccountActivation serviceAccountActivation = RaccordoPartitarioContiRaccordoActivateMapper.mapToDomain(request);
        ServiceAccountActivationResult result = activateServiceAccountService.activateServiceAccount(serviceAccountActivation);
        return ResponseEntity.status(HttpStatus.CREATED).body(RaccordoPartitarioContiRaccordoActivateMapper.mapToDtoResp(result));
    }
}
