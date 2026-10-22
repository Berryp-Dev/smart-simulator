package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.dto.ActivateDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.dto.ActivateDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model.ServiceAccountActivation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model.ServiceAccountActivationResult;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoActivateMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        ActivateDtoRequest dto = SamplePopulator.populate(ActivateDtoRequest.class);

        ServiceAccountActivation domain = RaccordoPartitarioContiRaccordoActivateMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountActivation domain = RaccordoPartitarioContiRaccordoActivateMapper.mapToDomain(new ActivateDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoActivateMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        ServiceAccountActivationResult domain = SamplePopulator.populate(ServiceAccountActivationResult.class);

        ActivateDtoResponse dto = RaccordoPartitarioContiRaccordoActivateMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        ServiceAccountActivationResult domain = SamplePopulator.populateWithNulls(ServiceAccountActivationResult.class);

        ActivateDtoResponse dto = RaccordoPartitarioContiRaccordoActivateMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoActivateMapper.mapToDtoResp(null));
    }
}
