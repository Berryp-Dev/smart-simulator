package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.SetSettlementServiceAccountKeyDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.model.SetSettlementServiceAccountKeyDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.domain.model.SettlementServiceAccountKeyAssignment;

/**
 * Mapping DTO <-> dominio dell'API setSettlementServiceAccountKey.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyMapper {

    private RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyMapper() {
    }

    public static SettlementServiceAccountKeyAssignment mapToDomain(SetSettlementServiceAccountKeyDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        SetSettlementServiceAccountKeyDtoRequestPayload payload = dto.getPayload();
        return new SettlementServiceAccountKeyAssignment(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDomain(payload == null ? null : payload.getSettlementAccountSrv()));
    }
}
