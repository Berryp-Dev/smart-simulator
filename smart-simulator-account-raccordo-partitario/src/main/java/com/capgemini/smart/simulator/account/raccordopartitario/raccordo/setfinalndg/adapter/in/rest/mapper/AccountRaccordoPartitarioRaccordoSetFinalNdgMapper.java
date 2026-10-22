package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCommonMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.adapter.in.rest.dto.SetFinalNdgDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.adapter.in.rest.dto.model.SetFinalNdgDtoRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.domain.model.FinalNdgAssignment;

/**
 * Mapping DTO <-> dominio dell'API setFinalNdg.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class AccountRaccordoPartitarioRaccordoSetFinalNdgMapper {

    private AccountRaccordoPartitarioRaccordoSetFinalNdgMapper() {
    }

    public static FinalNdgAssignment mapToDomain(SetFinalNdgDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        SetFinalNdgDtoRequestPayload payload = dto.getPayload();
        return new FinalNdgAssignment(
                AccountRaccordoPartitarioRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapNdgInfoToDomain(payload == null ? null : payload.getNdgInfo()));
    }
}
