package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKeySearchCriteria;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoGetExternalKeyMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        GetExternalKeyDtoRequest dto = SamplePopulator.populate(GetExternalKeyDtoRequest.class);

        ServiceAccountExternalKeySearchCriteria domain = RaccordoPartitarioContiRaccordoGetExternalKeyMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountExternalKeySearchCriteria domain = RaccordoPartitarioContiRaccordoGetExternalKeyMapper.mapToDomain(new GetExternalKeyDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoGetExternalKeyMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        ServiceAccountExternalKey domain = SamplePopulator.populate(ServiceAccountExternalKey.class);

        GetExternalKeyDtoResponse dto = RaccordoPartitarioContiRaccordoGetExternalKeyMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        ServiceAccountExternalKey domain = SamplePopulator.populateWithNulls(ServiceAccountExternalKey.class);

        GetExternalKeyDtoResponse dto = RaccordoPartitarioContiRaccordoGetExternalKeyMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoGetExternalKeyMapper.mapToDtoResp(null));
    }
}
