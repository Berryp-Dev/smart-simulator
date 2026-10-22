package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.adapter.in.rest.dto.SetFinalNdgDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.domain.model.FinalNdgAssignment;
import com.capgemini.smart.simulator.account.raccordopartitario.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class AccountRaccordoPartitarioRaccordoSetFinalNdgMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        SetFinalNdgDtoRequest dto = SamplePopulator.populate(SetFinalNdgDtoRequest.class);

        FinalNdgAssignment domain = AccountRaccordoPartitarioRaccordoSetFinalNdgMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        FinalNdgAssignment domain = AccountRaccordoPartitarioRaccordoSetFinalNdgMapper.mapToDomain(new SetFinalNdgDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoSetFinalNdgMapper.mapToDomain(null));
    }
}
