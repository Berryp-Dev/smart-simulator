package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.dto.CreateDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.dto.CreateDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model.ServiceAccountCreation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model.ServiceAccountCreationResult;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoCreateMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        CreateDtoRequest dto = SamplePopulator.populate(CreateDtoRequest.class);

        ServiceAccountCreation domain = RaccordoPartitarioContiRaccordoCreateMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountCreation domain = RaccordoPartitarioContiRaccordoCreateMapper.mapToDomain(new CreateDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoCreateMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        ServiceAccountCreationResult domain = SamplePopulator.populate(ServiceAccountCreationResult.class);

        CreateDtoResponse dto = RaccordoPartitarioContiRaccordoCreateMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        ServiceAccountCreationResult domain = SamplePopulator.populateWithNulls(ServiceAccountCreationResult.class);

        CreateDtoResponse dto = RaccordoPartitarioContiRaccordoCreateMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoCreateMapper.mapToDtoResp(null));
    }
}
