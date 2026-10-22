package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.dto.SetFinalNdgDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoSetFinalNdgMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.application.port.in.UpdateFinalNdgService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.domain.model.FinalNdgAssignment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API setFinalNdg (POST /smartengine/raccordo/setfinalndg).
 * Contratto REPLICA ORIGINALE: path, request e response come da raccordopartitario_sales.yaml.
 */
@RestController
@Tag(name = "Raccordo Partitario Conti / Raccordo")
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class RaccordoPartitarioContiRaccordoSetFinalNdgRestResource {

    @Autowired
    private UpdateFinalNdgService updateFinalNdgService;

    @Operation(operationId = "setFinalNdg")
    @PostMapping("/smartengine/raccordo/setfinalndg")
    public ResponseEntity<Void> setFinalNdg(@RequestBody SetFinalNdgDtoRequest request) {
        FinalNdgAssignment finalNdgAssignment = RaccordoPartitarioContiRaccordoSetFinalNdgMapper.mapToDomain(request);
        updateFinalNdgService.updateFinalNdg(finalNdgAssignment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
