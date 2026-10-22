package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.adapter.in.rest.dto.GetAdditionalAttributesDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.adapter.in.rest.dto.GetAdditionalAttributesDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributes;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributesSearchCriteria;
import com.capgemini.smart.simulator.account.raccordopartitario.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class AccountRaccordoPartitarioRaccordoGetAdditionalAttributesMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        GetAdditionalAttributesDtoRequest dto = SamplePopulator.populate(GetAdditionalAttributesDtoRequest.class);

        ServiceAccountAdditionalAttributesSearchCriteria domain = AccountRaccordoPartitarioRaccordoGetAdditionalAttributesMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountAdditionalAttributesSearchCriteria domain = AccountRaccordoPartitarioRaccordoGetAdditionalAttributesMapper.mapToDomain(new GetAdditionalAttributesDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoGetAdditionalAttributesMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        ServiceAccountAdditionalAttributes domain = SamplePopulator.populate(ServiceAccountAdditionalAttributes.class);

        GetAdditionalAttributesDtoResponse dto = AccountRaccordoPartitarioRaccordoGetAdditionalAttributesMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        ServiceAccountAdditionalAttributes domain = SamplePopulator.populateWithNulls(ServiceAccountAdditionalAttributes.class);

        GetAdditionalAttributesDtoResponse dto = AccountRaccordoPartitarioRaccordoGetAdditionalAttributesMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoGetAdditionalAttributesMapper.mapToDtoResp(null));
    }
}
