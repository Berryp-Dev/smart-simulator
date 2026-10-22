package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCommonMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.CreateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.CreateDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.model.CreateDtoRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.domain.model.ServiceAccountCreation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.domain.model.ServiceAccountCreationResult;

/**
 * Mapping DTO <-> dominio dell'API create.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class AccountRaccordoPartitarioRaccordoCreateMapper {

    private AccountRaccordoPartitarioRaccordoCreateMapper() {
    }

    public static ServiceAccountCreation mapToDomain(CreateDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        CreateDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountCreation(
                AccountRaccordoPartitarioRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvActnDataToDomain(payload == null ? null : payload.getSrvActnData()));
    }

    public static CreateDtoResponse mapToDtoResp(ServiceAccountCreationResult domain) {
        if (domain == null) {
            return null;
        }
        CreateDtoResponse dto = new CreateDtoResponse();
        dto.setSrvAcntKey(AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvAcntKeyToDto(domain.srvAcntKey()));
        dto.setStatusInfo(AccountRaccordoPartitarioRaccordoCommonMapper.mapStatusInfoRPToDto(domain.statusInfo()));
        return dto;
    }
}
