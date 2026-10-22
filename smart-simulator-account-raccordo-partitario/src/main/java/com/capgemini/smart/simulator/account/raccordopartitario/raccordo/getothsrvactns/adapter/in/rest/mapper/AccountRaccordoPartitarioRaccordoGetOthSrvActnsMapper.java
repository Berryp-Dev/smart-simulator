package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCommonMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.dto.model.GetOthSrvActnsDtoRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model.OtherServiceAccounts;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model.OtherServiceAccountsSearchCriteria;

/**
 * Mapping DTO <-> dominio dell'API getOthSrvActns.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class AccountRaccordoPartitarioRaccordoGetOthSrvActnsMapper {

    private AccountRaccordoPartitarioRaccordoGetOthSrvActnsMapper() {
    }

    public static OtherServiceAccountsSearchCriteria mapToDomain(GetOthSrvActnsDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        GetOthSrvActnsDtoRequestPayload payload = dto.getPayload();
        return new OtherServiceAccountsSearchCriteria(
                AccountRaccordoPartitarioRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static GetOthSrvActnsDtoResponse mapToDtoResp(OtherServiceAccounts domain) {
        if (domain == null) {
            return null;
        }
        GetOthSrvActnsDtoResponse dto = new GetOthSrvActnsDtoResponse();
        dto.setSrvActnData(AccountRaccordoPartitarioRaccordoCommonMapper.mapList(domain.srvActnData(), AccountRaccordoPartitarioRaccordoCommonMapper::mapSrvActnDataToDto));
        return dto;
    }
}
