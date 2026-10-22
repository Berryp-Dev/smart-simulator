package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.dto.GetAdditionalAttributesDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.dto.GetAdditionalAttributesDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.dto.model.AdditionalAttributes;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.dto.model.GetAdditionalAttributesDtoRequestPayload;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributes;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributesSearchCriteria;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.component.AdditionalAttributeGroup;

/**
 * Mapping DTO <-> dominio dell'API getAdditionalAttributes.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoGetAdditionalAttributesMapper {

    private RaccordoPartitarioContiRaccordoGetAdditionalAttributesMapper() {
    }

    public static ServiceAccountAdditionalAttributesSearchCriteria mapToDomain(GetAdditionalAttributesDtoRequest dto) {
        if (dto == null) {
            return null;
        }
        GetAdditionalAttributesDtoRequestPayload payload = dto.getPayload();
        return new ServiceAccountAdditionalAttributesSearchCriteria(
                RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getCaseID(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(payload == null ? null : payload.getSrvAcntKey()));
    }

    public static GetAdditionalAttributesDtoResponse mapToDtoResp(ServiceAccountAdditionalAttributes domain) {
        if (domain == null) {
            return null;
        }
        GetAdditionalAttributesDtoResponse dto = new GetAdditionalAttributesDtoResponse();
        dto.setAccountAdditionalAttributes(mapAdditionalAttributesToDto(domain.accountAdditionalAttributes()));
        dto.setContractAdditionalAttributes(mapAdditionalAttributesToDto(domain.contractAdditionalAttributes()));
        return dto;
    }

    public static AdditionalAttributes mapAdditionalAttributesToDto(AdditionalAttributeGroup domain) {
        if (domain == null) {
            return null;
        }
        AdditionalAttributes dto = new AdditionalAttributes();
        dto.setQualifiers(RaccordoPartitarioContiRaccordoCommonMapper.mapList(domain.qualifiers(), RaccordoPartitarioContiRaccordoCommonMapper::mapAdditionalAttributeToDto));
        dto.setAncillaryData(RaccordoPartitarioContiRaccordoCommonMapper.mapList(domain.ancillaryData(), RaccordoPartitarioContiRaccordoCommonMapper::mapAdditionalAttributeToDto));
        return dto;
    }
}
