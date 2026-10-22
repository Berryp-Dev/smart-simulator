package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.model.GetOthSrvActnsDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.domain.model.OtherServiceAccounts;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.domain.model.OtherServiceAccountsSearchCriteria;

/**
 * Mapping DTO <-> dominio dell'API getOthSrvActns.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper {

    private RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper() {
    }

    public static OtherServiceAccountsSearchCriteria mapToDomain(GetOthSrvActnsDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        GetOthSrvActnsDtoRequestPayload payload = dto.getPayload();
        return new OtherServiceAccountsSearchCriteria(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static GetOthSrvActnsDtoResponse mapToDtoResp(OtherServiceAccounts domain) {
        if (domain == null) {
            return null;
        }
        GetOthSrvActnsDtoResponse dto = new GetOthSrvActnsDtoResponse();
        dto.setSrvActnData(RaccordoPartitarioContiRaccordoCommonMapper.mapList(domain.srvActnData(), RaccordoPartitarioContiRaccordoCommonMapper::mapSrvActnDataToDto));
        return dto;
    }
}
