package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.CreateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.CreateDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.domain.model.ServiceAccountCreation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.domain.model.ServiceAccountCreationResult;
import com.capgemini.smart.simulator.account.raccordopartitario.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class AccountRaccordoPartitarioRaccordoCreateMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        CreateDtoRequest dto = SamplePopulator.populate(CreateDtoRequest.class);

        ServiceAccountCreation domain = AccountRaccordoPartitarioRaccordoCreateMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountCreation domain = AccountRaccordoPartitarioRaccordoCreateMapper.mapToDomain(new CreateDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoCreateMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        ServiceAccountCreationResult domain = SamplePopulator.populate(ServiceAccountCreationResult.class);

        CreateDtoResponse dto = AccountRaccordoPartitarioRaccordoCreateMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        ServiceAccountCreationResult domain = SamplePopulator.populateWithNulls(ServiceAccountCreationResult.class);

        CreateDtoResponse dto = AccountRaccordoPartitarioRaccordoCreateMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoCreateMapper.mapToDtoResp(null));
    }
}
