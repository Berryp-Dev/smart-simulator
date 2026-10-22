package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCommonMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.SetSettlementServiceAccountKeyDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.model.SetSettlementServiceAccountKeyDtoRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.domain.model.SettlementServiceAccountKeyAssignment;

/**
 * Mapping DTO <-> dominio dell'API setSettlementServiceAccountKey.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyMapper {

    private AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyMapper() {
    }

    public static SettlementServiceAccountKeyAssignment mapToDomain(SetSettlementServiceAccountKeyDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        SetSettlementServiceAccountKeyDtoRequestPayload payload = dto.getPayload();
        return new SettlementServiceAccountKeyAssignment(
                AccountRaccordoPartitarioRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvAcntExtKeyToDomain(payload == null ? null : payload.getSettlementAccountSrv()));
    }
}
