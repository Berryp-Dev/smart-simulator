package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfoSearchCriteria;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoGetMainInfoMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        GetMainInfoDtoRequest dto = SamplePopulator.populate(GetMainInfoDtoRequest.class);

        ServiceAccountMainInfoSearchCriteria domain = RaccordoPartitarioContiRaccordoGetMainInfoMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountMainInfoSearchCriteria domain = RaccordoPartitarioContiRaccordoGetMainInfoMapper.mapToDomain(new GetMainInfoDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoGetMainInfoMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        ServiceAccountMainInfo domain = SamplePopulator.populate(ServiceAccountMainInfo.class);

        GetMainInfoDtoResponse dto = RaccordoPartitarioContiRaccordoGetMainInfoMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        ServiceAccountMainInfo domain = SamplePopulator.populateWithNulls(ServiceAccountMainInfo.class);

        GetMainInfoDtoResponse dto = RaccordoPartitarioContiRaccordoGetMainInfoMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoGetMainInfoMapper.mapToDtoResp(null));
    }
}
