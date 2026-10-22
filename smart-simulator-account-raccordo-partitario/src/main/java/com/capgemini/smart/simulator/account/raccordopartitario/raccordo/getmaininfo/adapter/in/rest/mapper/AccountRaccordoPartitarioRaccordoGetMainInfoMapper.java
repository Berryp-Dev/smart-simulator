package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCommonMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.adapter.in.rest.dto.model.GetMainInfoDtoRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.domain.model.ServiceAccountMainInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.domain.model.ServiceAccountMainInfoSearchCriteria;

/**
 * Mapping DTO <-> dominio dell'API getMainInfo.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class AccountRaccordoPartitarioRaccordoGetMainInfoMapper {

    private AccountRaccordoPartitarioRaccordoGetMainInfoMapper() {
    }

    public static ServiceAccountMainInfoSearchCriteria mapToDomain(GetMainInfoDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        GetMainInfoDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountMainInfoSearchCriteria(
                AccountRaccordoPartitarioRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static GetMainInfoDtoResponse mapToDtoResp(ServiceAccountMainInfo domain) {
        if (domain == null) {
            return null;
        }
        GetMainInfoDtoResponse dto = new GetMainInfoDtoResponse();
        dto.setSrvActnData(AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvActnDataToDto(domain.srvActnData()));
        return dto;
    }
}
