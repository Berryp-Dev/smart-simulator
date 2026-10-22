package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.dto.SetFinalNdgDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.dto.model.SetFinalNdgDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.domain.model.FinalNdgAssignment;

/**
 * Mapping DTO <-> dominio dell'API setFinalNdg.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoSetFinalNdgMapper {

    private RaccordoPartitarioContiRaccordoSetFinalNdgMapper() {
    }

    public static FinalNdgAssignment mapToDomain(SetFinalNdgDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        SetFinalNdgDtoRequestPayload payload = dto.getPayload();
        return new FinalNdgAssignment(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()),
                RaccordoPartitarioContiRaccordoCommonMapper.mapNdgInfoToDomain(payload == null ? null : payload.getNdgInfo()));
    }
}
