package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.CanBeCreatedRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.CanBeCreatedResponse;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.enums.SimpleProductNdgNature;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.model.SimpleProductNdgInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.domain.enums.NdgNatureCode;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.domain.model.ServiceAccountCreationCheck;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.domain.model.ServiceAccountCreationCheckResult;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.domain.model.component.PreCreationNdgInformation;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoCanBeCreatedMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        CanBeCreatedRequest dto = SamplePopulator.populate(CanBeCreatedRequest.class);

        ServiceAccountCreationCheck domain = RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountCreationCheck domain = RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapToDomain(new CanBeCreatedRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        ServiceAccountCreationCheckResult domain = SamplePopulator.populate(ServiceAccountCreationCheckResult.class);

        CanBeCreatedResponse dto = RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        ServiceAccountCreationCheckResult domain = SamplePopulator.populateWithNulls(ServiceAccountCreationCheckResult.class);

        CanBeCreatedResponse dto = RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapToDtoResp(null));
    }

    @Test
    void SimpleProductNdgNature_allConstants_haveSameNameInDomainEnum() {
        Set<String> dtoNames = Arrays.stream(SimpleProductNdgNature.values()).map(Enum::name).collect(Collectors.toSet());
        Set<String> domainNames = Arrays.stream(NdgNatureCode.values()).map(Enum::name).collect(Collectors.toSet());

        assertEquals(dtoNames, domainNames);
        for (SimpleProductNdgNature constant : SimpleProductNdgNature.values()) {
            assertEquals(constant.name(), RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapSimpleProductNdgNatureToDomain(constant).name());
        }
        for (NdgNatureCode constant : NdgNatureCode.values()) {
            assertEquals(constant.name(), RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapSimpleProductNdgNatureToDto(constant).name());
        }
        assertNull(RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapSimpleProductNdgNatureToDomain(null));
    }

    @Test
    void mapSimpleProductNdgInfoToDomain_populatedChild_copiesParentFieldsToo() {
        SimpleProductNdgInfo dto = SamplePopulator.populate(SimpleProductNdgInfo.class);

        PreCreationNdgInformation domain = RaccordoPartitarioContiRaccordoCanBeCreatedMapper.mapSimpleProductNdgInfoToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }
}
