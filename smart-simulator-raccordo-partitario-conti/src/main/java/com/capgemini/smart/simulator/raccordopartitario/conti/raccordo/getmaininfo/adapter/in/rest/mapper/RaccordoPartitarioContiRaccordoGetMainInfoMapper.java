package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.model.GetMainInfoDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfoSearchCriteria;

/**
 * Mapping DTO <-> dominio dell'API getMainInfo.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoGetMainInfoMapper {

    private RaccordoPartitarioContiRaccordoGetMainInfoMapper() {
    }

    public static ServiceAccountMainInfoSearchCriteria mapToDomain(GetMainInfoDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        GetMainInfoDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountMainInfoSearchCriteria(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static GetMainInfoDtoResponse mapToDtoResp(ServiceAccountMainInfo domain) {
        if (domain == null) {
            return null;
        }
        GetMainInfoDtoResponse dto = new GetMainInfoDtoResponse();
        dto.setSrvActnData(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvActnDataToDto(domain.srvActnData()));
        return dto;
    }
}
