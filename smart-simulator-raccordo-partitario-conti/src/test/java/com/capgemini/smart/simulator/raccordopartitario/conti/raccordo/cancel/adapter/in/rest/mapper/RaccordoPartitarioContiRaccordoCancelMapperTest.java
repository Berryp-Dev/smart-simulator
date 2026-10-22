package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.adapter.in.rest.dto.CancelDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.domain.model.ServiceAccountCancellation;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoCancelMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        CancelDtoRequest dto = SamplePopulator.populate(CancelDtoRequest.class);

        ServiceAccountCancellation domain = RaccordoPartitarioContiRaccordoCancelMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountCancellation domain = RaccordoPartitarioContiRaccordoCancelMapper.mapToDomain(new CancelDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoCancelMapper.mapToDomain(null));
    }
}
