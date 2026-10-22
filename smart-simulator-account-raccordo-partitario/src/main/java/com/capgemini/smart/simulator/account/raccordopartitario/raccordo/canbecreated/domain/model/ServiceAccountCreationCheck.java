package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component.ServiceAccountPreCreationData;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.domain.model.component.ServiceAccountExtendedKey;
import java.util.List;
import java.util.Map;

/**
 * Modello di dominio (request) corrispondente allo schema YAML {@code CanBeCreatedRequest}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record ServiceAccountCreationCheck(
        RequestHeader header,
        Map<String, Object> additionalUserData,
        ServiceAccountExtendedKey settlementSrvActn,
        List<ServiceAccountPreCreationData> srvActnPreDatas) {
}
