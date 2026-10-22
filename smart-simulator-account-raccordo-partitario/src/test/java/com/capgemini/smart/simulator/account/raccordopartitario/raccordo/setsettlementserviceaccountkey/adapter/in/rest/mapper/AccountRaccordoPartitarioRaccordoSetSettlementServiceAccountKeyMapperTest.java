package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.SetSettlementServiceAccountKeyDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.domain.model.SettlementServiceAccountKeyAssignment;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        SetSettlementServiceAccountKeyDtoRequest dto = SamplePopulator.populate(SetSettlementServiceAccountKeyDtoRequest.class);

        SettlementServiceAccountKeyAssignment domain = RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        SettlementServiceAccountKeyAssignment domain = RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyMapper.mapToDomain(new SetSettlementServiceAccountKeyDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyMapper.mapToDomain(null));
    }
}
