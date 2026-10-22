package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.dto.ActivateDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.dto.ActivateDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.dto.model.ActivateDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model.ServiceAccountActivation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model.ServiceAccountActivationResult;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;

/**
 * Mapping DTO <-> dominio dell'API activate.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoActivateMapper {

    private RaccordoPartitarioContiRaccordoActivateMapper() {
    }

    public static ServiceAccountActivation mapToDomain(ActivateDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        ActivateDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountActivation(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static ActivateDtoResponse mapToDtoResp(ServiceAccountActivationResult domain) {
        if (domain == null) {
            return null;
        }
        ActivateDtoResponse dto = new ActivateDtoResponse();
        dto.setNewAncillaryData(RaccordoPartitarioContiRaccordoCommonMapper.mapList(domain.newAncillaryData(), RaccordoPartitarioContiRaccordoCommonMapper::mapAdditionalAttributeToDto));
        return dto;
    }
}
