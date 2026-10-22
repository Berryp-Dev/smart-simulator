package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoGetMainInfoMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.application.port.in.RetrieveServiceAccountMainInfoService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfoSearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API getMainInfo (POST /smartengine/raccordo/getmaininfo).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class RaccordoPartitarioContiRaccordoGetMainInfoRestResource {

    @Autowired
    private RetrieveServiceAccountMainInfoService retrieveServiceAccountMainInfoService;

    @Operation(operationId = "getMainInfo")
    @PostMapping("/smartengine/raccordo/getmaininfo")
    public ResponseEntity<GetMainInfoDtoResponse> getMainInfo(@RequestBody GetMainInfoDtoRequest request) {
        ServiceAccountMainInfoSearchCriteria serviceAccountMainInfoSearchCriteria = RaccordoPartitarioContiRaccordoGetMainInfoMapper.mapToDomain(request);
        ServiceAccountMainInfo result = retrieveServiceAccountMainInfoService.getServiceAccountMainInfo(serviceAccountMainInfoSearchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(RaccordoPartitarioContiRaccordoGetMainInfoMapper.mapToDtoResp(result));
    }
}
