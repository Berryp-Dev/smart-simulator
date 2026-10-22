package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.domain.model.OtherServiceAccounts;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.domain.model.OtherServiceAccountsSearchCriteria;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoGetOthSrvActnsMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        GetOthSrvActnsDtoRequest dto = SamplePopulator.populate(GetOthSrvActnsDtoRequest.class);

        OtherServiceAccountsSearchCriteria domain = RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        OtherServiceAccountsSearchCriteria domain = RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper.mapToDomain(new GetOthSrvActnsDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        OtherServiceAccounts domain = SamplePopulator.populate(OtherServiceAccounts.class);

        GetOthSrvActnsDtoResponse dto = RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        OtherServiceAccounts domain = SamplePopulator.populateWithNulls(OtherServiceAccounts.class);

        GetOthSrvActnsDtoResponse dto = RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoGetOthSrvActnsMapper.mapToDtoResp(null));
    }
}
