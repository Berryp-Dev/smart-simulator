package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.NdgInformation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountKey;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code SetFinalNdgDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record FinalNdgAssignment(
        RequestHeader header,
        String caseID,
        ServiceAccountKey srvAcntKey,
        NdgInformation ndgInfo) {
}
