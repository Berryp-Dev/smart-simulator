package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.adapter.in.rest.dto.CancelDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.domain.model.ServiceAccountCancellation;
import com.capgemini.smart.simulator.account.raccordopartitario.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class AccountRaccordoPartitarioRaccordoCancelMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        CancelDtoRequest dto = SamplePopulator.populate(CancelDtoRequest.class);

        ServiceAccountCancellation domain = AccountRaccordoPartitarioRaccordoCancelMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountCancellation domain = AccountRaccordoPartitarioRaccordoCancelMapper.mapToDomain(new CancelDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoCancelMapper.mapToDomain(null));
    }
}
