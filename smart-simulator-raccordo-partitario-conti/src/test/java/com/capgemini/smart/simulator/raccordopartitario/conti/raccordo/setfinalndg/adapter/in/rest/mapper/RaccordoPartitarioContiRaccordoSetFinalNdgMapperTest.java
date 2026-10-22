package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.dto.SetFinalNdgDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.domain.model.FinalNdgAssignment;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoSetFinalNdgMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        SetFinalNdgDtoRequest dto = SamplePopulator.populate(SetFinalNdgDtoRequest.class);

        FinalNdgAssignment domain = RaccordoPartitarioContiRaccordoSetFinalNdgMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        FinalNdgAssignment domain = RaccordoPartitarioContiRaccordoSetFinalNdgMapper.mapToDomain(new SetFinalNdgDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoSetFinalNdgMapper.mapToDomain(null));
    }
}
