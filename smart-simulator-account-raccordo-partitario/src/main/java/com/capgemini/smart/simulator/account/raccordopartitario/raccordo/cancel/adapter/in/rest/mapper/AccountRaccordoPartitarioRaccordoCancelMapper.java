package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.adapter.in.rest.dto.CancelDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.adapter.in.rest.dto.model.CancelDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.domain.model.ServiceAccountCancellation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;

/**
 * Mapping DTO <-> dominio dell'API cancel.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoCancelMapper {

    private RaccordoPartitarioContiRaccordoCancelMapper() {
    }

    public static ServiceAccountCancellation mapToDomain(CancelDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        CancelDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountCancellation(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }
}
