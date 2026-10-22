package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.enums.StatusInfoRPStatus;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.AdditionalAttribute;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.Header;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.NdgInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.ProdInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.ProdInfoAgreement;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.ProdInfoProdAttr;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.SrvAcntExtKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.SrvAcntKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.SrvActnData;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model.StatusInfoRP;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.enums.ServiceAccountStatusCode;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.AdditionalAttributeItem;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.NdgInformation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ProductAgreement;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ProductAttribute;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ProductInformation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.RequestHeader;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountData;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountExtendedKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.ServiceAccountKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.model.component.StatusInformation;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class RaccordoPartitarioContiRaccordoCommonMapperTest {

    @Test
    void mapHeaderToDomain_populatedDto_mapsEveryField() {
        Header dto = SamplePopulator.populate(Header.class);

        RequestHeader domain = RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(null));
    }

    @Test
    void mapHeaderToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapHeaderToDomain(new Header()));
    }

    @Test
    void mapSrvAcntKeyToDomain_populatedDto_mapsEveryField() {
        SrvAcntKey dto = SamplePopulator.populate(SrvAcntKey.class);

        ServiceAccountKey domain = RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(null));
    }

    @Test
    void mapSrvAcntKeyToDto_populatedDomain_mapsEveryField() {
        ServiceAccountKey domain = SamplePopulator.populate(ServiceAccountKey.class);

        SrvAcntKey dto = RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDto(null));
    }

    @Test
    void mapSrvAcntKey_roundTrip_returnsEqualDto() {
        SrvAcntKey dto = SamplePopulator.populate(SrvAcntKey.class);

        assertEquals(dto, RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(dto)));
    }

    @Test
    void mapSrvAcntKeyToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(new SrvAcntKey()));
    }

    @Test
    void mapSrvAcntExtKeyToDomain_populatedDto_mapsEveryField() {
        SrvAcntExtKey dto = SamplePopulator.populate(SrvAcntExtKey.class);

        ServiceAccountExtendedKey domain = RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDomain(null));
    }

    @Test
    void mapSrvAcntExtKeyToDto_populatedDomain_mapsEveryField() {
        ServiceAccountExtendedKey domain = SamplePopulator.populate(ServiceAccountExtendedKey.class);

        SrvAcntExtKey dto = RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDto(null));
    }

    @Test
    void mapSrvAcntExtKey_roundTrip_returnsEqualDto() {
        SrvAcntExtKey dto = SamplePopulator.populate(SrvAcntExtKey.class);

        assertEquals(dto, RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDomain(dto)));
    }

    @Test
    void mapSrvAcntExtKeyToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntExtKeyToDomain(new SrvAcntExtKey()));
    }

    @Test
    void mapNdgInfoToDomain_populatedDto_mapsEveryField() {
        NdgInfo dto = SamplePopulator.populate(NdgInfo.class);

        NdgInformation domain = RaccordoPartitarioContiRaccordoCommonMapper.mapNdgInfoToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapNdgInfoToDomain(null));
    }

    @Test
    void mapNdgInfoToDto_populatedDomain_mapsEveryField() {
        NdgInformation domain = SamplePopulator.populate(NdgInformation.class);

        NdgInfo dto = RaccordoPartitarioContiRaccordoCommonMapper.mapNdgInfoToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapNdgInfoToDto(null));
    }

    @Test
    void mapNdgInfo_roundTrip_returnsEqualDto() {
        NdgInfo dto = SamplePopulator.populate(NdgInfo.class);

        assertEquals(dto, RaccordoPartitarioContiRaccordoCommonMapper.mapNdgInfoToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapNdgInfoToDomain(dto)));
    }

    @Test
    void mapNdgInfoToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapNdgInfoToDomain(new NdgInfo()));
    }

    @Test
    void mapAdditionalAttributeToDto_populatedDomain_mapsEveryField() {
        AdditionalAttributeItem domain = SamplePopulator.populate(AdditionalAttributeItem.class);

        AdditionalAttribute dto = RaccordoPartitarioContiRaccordoCommonMapper.mapAdditionalAttributeToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapAdditionalAttributeToDto(null));
    }

    @Test
    void mapProdInfoAgreementToDomain_populatedDto_mapsEveryField() {
        ProdInfoAgreement dto = SamplePopulator.populate(ProdInfoAgreement.class);

        ProductAgreement domain = RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoAgreementToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoAgreementToDomain(null));
    }

    @Test
    void mapProdInfoAgreementToDto_populatedDomain_mapsEveryField() {
        ProductAgreement domain = SamplePopulator.populate(ProductAgreement.class);

        ProdInfoAgreement dto = RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoAgreementToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoAgreementToDto(null));
    }

    @Test
    void mapProdInfoAgreement_roundTrip_returnsEqualDto() {
        ProdInfoAgreement dto = SamplePopulator.populate(ProdInfoAgreement.class);

        assertEquals(dto, RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoAgreementToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoAgreementToDomain(dto)));
    }

    @Test
    void mapProdInfoAgreementToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoAgreementToDomain(new ProdInfoAgreement()));
    }

    @Test
    void mapProdInfoProdAttrToDomain_populatedDto_mapsEveryField() {
        ProdInfoProdAttr dto = SamplePopulator.populate(ProdInfoProdAttr.class);

        ProductAttribute domain = RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoProdAttrToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoProdAttrToDomain(null));
    }

    @Test
    void mapProdInfoProdAttrToDto_populatedDomain_mapsEveryField() {
        ProductAttribute domain = SamplePopulator.populate(ProductAttribute.class);

        ProdInfoProdAttr dto = RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoProdAttrToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoProdAttrToDto(null));
    }

    @Test
    void mapProdInfoProdAttr_roundTrip_returnsEqualDto() {
        ProdInfoProdAttr dto = SamplePopulator.populate(ProdInfoProdAttr.class);

        assertEquals(dto, RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoProdAttrToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoProdAttrToDomain(dto)));
    }

    @Test
    void mapProdInfoProdAttrToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoProdAttrToDomain(new ProdInfoProdAttr()));
    }

    @Test
    void mapProdInfoToDomain_populatedDto_mapsEveryField() {
        ProdInfo dto = SamplePopulator.populate(ProdInfo.class);

        ProductInformation domain = RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoToDomain(null));
    }

    @Test
    void mapProdInfoToDto_populatedDomain_mapsEveryField() {
        ProductInformation domain = SamplePopulator.populate(ProductInformation.class);

        ProdInfo dto = RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoToDto(null));
    }

    @Test
    void mapProdInfo_roundTrip_returnsEqualDto() {
        ProdInfo dto = SamplePopulator.populate(ProdInfo.class);

        assertEquals(dto, RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoToDomain(dto)));
    }

    @Test
    void mapProdInfoToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapProdInfoToDomain(new ProdInfo()));
    }

    @Test
    void mapStatusInfoRPToDomain_populatedDto_mapsEveryField() {
        StatusInfoRP dto = SamplePopulator.populate(StatusInfoRP.class);

        StatusInformation domain = RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPToDomain(null));
    }

    @Test
    void mapStatusInfoRPToDto_populatedDomain_mapsEveryField() {
        StatusInformation domain = SamplePopulator.populate(StatusInformation.class);

        StatusInfoRP dto = RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPToDto(null));
    }

    @Test
    void mapStatusInfoRP_roundTrip_returnsEqualDto() {
        StatusInfoRP dto = SamplePopulator.populate(StatusInfoRP.class);

        assertEquals(dto, RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPToDomain(dto)));
    }

    @Test
    void mapStatusInfoRPToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPToDomain(new StatusInfoRP()));
    }

    @Test
    void mapSrvActnDataToDomain_populatedDto_mapsEveryField() {
        SrvActnData dto = SamplePopulator.populate(SrvActnData.class);

        ServiceAccountData domain = RaccordoPartitarioContiRaccordoCommonMapper.mapSrvActnDataToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvActnDataToDomain(null));
    }

    @Test
    void mapSrvActnDataToDto_populatedDomain_mapsEveryField() {
        ServiceAccountData domain = SamplePopulator.populate(ServiceAccountData.class);

        SrvActnData dto = RaccordoPartitarioContiRaccordoCommonMapper.mapSrvActnDataToDto(domain);

        SamplePopulator.assertFullyPopulated(dto);
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvActnDataToDto(null));
    }

    @Test
    void mapSrvActnData_roundTrip_returnsEqualDto() {
        SrvActnData dto = SamplePopulator.populate(SrvActnData.class);

        assertEquals(dto, RaccordoPartitarioContiRaccordoCommonMapper.mapSrvActnDataToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvActnDataToDomain(dto)));
    }

    @Test
    void mapSrvActnDataToDomain_emptyDto_keepsNullsWithoutErrors() {
        SamplePopulator.assertAllComponentsNull(RaccordoPartitarioContiRaccordoCommonMapper.mapSrvActnDataToDomain(new SrvActnData()));
    }

    @Test
    void StatusInfoRPStatus_allConstants_haveSameNameInDomainEnum() {
        Set<String> dtoNames = Arrays.stream(StatusInfoRPStatus.values()).map(Enum::name).collect(Collectors.toSet());
        Set<String> domainNames = Arrays.stream(ServiceAccountStatusCode.values()).map(Enum::name).collect(Collectors.toSet());

        assertEquals(dtoNames, domainNames);
        for (StatusInfoRPStatus constant : StatusInfoRPStatus.values()) {
            assertEquals(constant.name(), RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPStatusToDomain(constant).name());
            assertEquals(constant.name(), RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPStatusToDto(RaccordoPartitarioContiRaccordoCommonMapper.mapStatusInfoRPStatusToDomain(constant)).name());
        }
    }

    @Test
    void mapList_nullList_returnsNull() {
        assertNull(RaccordoPartitarioContiRaccordoCommonMapper.mapList(null, element -> element));
    }
}
