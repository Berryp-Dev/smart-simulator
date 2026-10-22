package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.dto.ReserveDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.dto.ReserveDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.dto.model.ReserveDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.domain.model.ServiceAccountReservation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.domain.model.ServiceAccountReservationResult;

/**
 * Mapping DTO <-> dominio dell'API reserve.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoReserveMapper {

    private RaccordoPartitarioContiRaccordoReserveMapper() {
    }

    public static ServiceAccountReservation mapToDomain(ReserveDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        ReserveDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountReservation(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static ReserveDtoResponse mapToDtoResp(ServiceAccountReservationResult domain) {
        if (domain == null) {
            return null;
        }
        ReserveDtoResponse dto = new ReserveDtoResponse();
        dto.setSrvAcntExtKey(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDto(domain.srvAcntExtKey()));
        return dto;
    }
}
