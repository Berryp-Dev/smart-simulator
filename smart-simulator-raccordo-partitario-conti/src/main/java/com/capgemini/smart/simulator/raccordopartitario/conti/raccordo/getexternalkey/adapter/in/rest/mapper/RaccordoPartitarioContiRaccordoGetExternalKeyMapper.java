package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.dto.model.GetExternalKeyDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKeySearchCriteria;

/**
 * Mapping DTO <-> dominio dell'API getExternalKey.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoGetExternalKeyMapper {

    private RaccordoPartitarioContiRaccordoGetExternalKeyMapper() {
    }

    public static ServiceAccountExternalKeySearchCriteria mapToDomain(GetExternalKeyDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        GetExternalKeyDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountExternalKeySearchCriteria(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static GetExternalKeyDtoResponse mapToDtoResp(ServiceAccountExternalKey domain) {
        if (domain == null) {
            return null;
        }
        GetExternalKeyDtoResponse dto = new GetExternalKeyDtoResponse();
        dto.setSrvAcntExtKey(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDto(domain.srvAcntExtKey()));
        return dto;
    }
}
