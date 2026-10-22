package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.SetSettlementServiceAccountKeyDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.domain.model.SettlementServiceAccountKeyAssignment;
import com.capgemini.smart.simulator.account.raccordopartitario.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        SetSettlementServiceAccountKeyDtoRequest dto = SamplePopulator.populate(SetSettlementServiceAccountKeyDtoRequest.class);

        SettlementServiceAccountKeyAssignment domain = AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        SettlementServiceAccountKeyAssignment domain = AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyMapper.mapToDomain(new SetSettlementServiceAccountKeyDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyMapper.mapToDomain(null));
    }
}
