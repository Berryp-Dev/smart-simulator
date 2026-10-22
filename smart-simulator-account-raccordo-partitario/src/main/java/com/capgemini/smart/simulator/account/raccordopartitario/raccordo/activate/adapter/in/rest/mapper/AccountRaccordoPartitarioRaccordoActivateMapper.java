package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.adapter.in.rest.dto.ActivateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.adapter.in.rest.dto.ActivateDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.adapter.in.rest.dto.model.ActivateDtoRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.domain.model.ServiceAccountActivation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.domain.model.ServiceAccountActivationResult;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCommonMapper;

/**
 * Mapping DTO <-> dominio dell'API activate.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class AccountRaccordoPartitarioRaccordoActivateMapper {

    private AccountRaccordoPartitarioRaccordoActivateMapper() {
    }

    public static ServiceAccountActivation mapToDomain(ActivateDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        ActivateDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountActivation(
                AccountRaccordoPartitarioRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static ActivateDtoResponse mapToDtoResp(ServiceAccountActivationResult domain) {
        if (domain == null) {
            return null;
        }
        ActivateDtoResponse dto = new ActivateDtoResponse();
        dto.setNewAncillaryData(AccountRaccordoPartitarioRaccordoCommonMapper.mapList(domain.newAncillaryData(), AccountRaccordoPartitarioRaccordoCommonMapper::mapAdditionalAttributeToDto));
        return dto;
    }
}
