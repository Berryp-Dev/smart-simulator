package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.domain.model;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountExtendedKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountKey;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code SetSettlementServiceAccountKeyDtoRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record SettlementServiceAccountKeyAssignment(
        RequestHeader header,
        String caseID,
        ServiceAccountKey srvAcntKey,
        ServiceAccountExtendedKey settlementAccountSrv) {
}
