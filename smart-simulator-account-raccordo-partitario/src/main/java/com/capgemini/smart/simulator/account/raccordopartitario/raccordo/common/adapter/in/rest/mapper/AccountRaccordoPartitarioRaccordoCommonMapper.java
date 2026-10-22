package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper;

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
import java.util.List;
import java.util.function.Function;

/**
 * Mapping DTO <-> dominio dei componenti condivisi tra le API dell'area Raccordo.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoCommonMapper {

    private RaccordoPartitarioContiRaccordoCommonMapper() {
    }

    public static RequestHeader mapHeaderToDomain(Header dto) {
        if (dto == null) {
            return null;
        }
        return new RequestHeader(
                dto.getTimestamp(),
                dto.getTrackId(),
                dto.getApplicationId(),
                dto.getOperationCode(),
                dto.getChannel(),
                dto.getTerminal(),
                dto.getCompanyCode(),
                dto.getBranchCode(),
                dto.getUser(),
                dto.getProfile(),
                dto.getLogicalChannel(),
                dto.getIdempotencyId(),
                dto.getTransactionId());
    }

    public static ServiceAccountKey mapSrvAcntKeyToDomain(SrvAcntKey dto) {
        if (dto == null) {
            return null;
        }
        return new ServiceAccountKey(
                dto.getBankCod(),
                dto.getSrvCod(),
                dto.getKey());
    }

    public static SrvAcntKey mapSrvAcntKeyToDto(ServiceAccountKey domain) {
        if (domain == null) {
            return null;
        }
        SrvAcntKey dto = new SrvAcntKey();
        dto.setBankCod(domain.bankCod());
        dto.setSrvCod(domain.srvCod());
        dto.setKey(domain.key());
        return dto;
    }

    public static ServiceAccountExtendedKey mapSrvAcntExtKeyToDomain(SrvAcntExtKey dto) {
        if (dto == null) {
            return null;
        }
        return new ServiceAccountExtendedKey(
                dto.getBankCod(),
                dto.getSrvCod(),
                dto.getKey(),
                dto.getOfficeCod());
    }

    public static SrvAcntExtKey mapSrvAcntExtKeyToDto(ServiceAccountExtendedKey domain) {
        if (domain == null) {
            return null;
        }
        SrvAcntExtKey dto = new SrvAcntExtKey();
        dto.setBankCod(domain.bankCod());
        dto.setSrvCod(domain.srvCod());
        dto.setKey(domain.key());
        dto.setOfficeCod(domain.officeCod());
        return dto;
    }

    public static NdgInformation mapNdgInfoToDomain(NdgInfo dto) {
        if (dto == null) {
            return null;
        }
        return new NdgInformation(
                dto.getCntrNdgKey(),
                dto.getCntrNdgType(),
                dto.getCntrSignNdgKeys());
    }

    public static NdgInfo mapNdgInfoToDto(NdgInformation domain) {
        if (domain == null) {
            return null;
        }
        NdgInfo dto = new NdgInfo();
        dto.setCntrNdgKey(domain.cntrNdgKey());
        dto.setCntrNdgType(domain.cntrNdgType());
        dto.setCntrSignNdgKeys(domain.cntrSignNdgKeys());
        return dto;
    }

    public static AdditionalAttribute mapAdditionalAttributeToDto(AdditionalAttributeItem domain) {
        if (domain == null) {
            return null;
        }
        AdditionalAttribute dto = new AdditionalAttribute();
        dto.setKey(domain.key());
        dto.setValue(domain.value());
        return dto;
    }

    public static ProductAgreement mapProdInfoAgreementToDomain(ProdInfoAgreement dto) {
        if (dto == null) {
            return null;
        }
        return new ProductAgreement(
                dto.getTy(),
                dto.getSTy(),
                dto.getKey());
    }

    public static ProdInfoAgreement mapProdInfoAgreementToDto(ProductAgreement domain) {
        if (domain == null) {
            return null;
        }
        ProdInfoAgreement dto = new ProdInfoAgreement();
        dto.setTy(domain.ty());
        dto.setSTy(domain.sTy());
        dto.setKey(domain.key());
        return dto;
    }

    public static ProductAttribute mapProdInfoProdAttrToDomain(ProdInfoProdAttr dto) {
        if (dto == null) {
            return null;
        }
        return new ProductAttribute(
                dto.getAttrCod(),
                dto.getAttrVal());
    }

    public static ProdInfoProdAttr mapProdInfoProdAttrToDto(ProductAttribute domain) {
        if (domain == null) {
            return null;
        }
        ProdInfoProdAttr dto = new ProdInfoProdAttr();
        dto.setAttrCod(domain.attrCod());
        dto.setAttrVal(domain.attrVal());
        return dto;
    }

    public static ProductInformation mapProdInfoToDomain(ProdInfo dto) {
        if (dto == null) {
            return null;
        }
        return new ProductInformation(
                dto.getProdKey(),
                dto.getModuleKeys(),
                mapList(dto.getAgrmnts(), RaccordoPartitarioContiRaccordoCommonMapper::mapProdInfoAgreementToDomain),
                dto.getProdExtKeys(),
                mapList(dto.getProdAttrs(), RaccordoPartitarioContiRaccordoCommonMapper::mapProdInfoProdAttrToDomain));
    }

    public static ProdInfo mapProdInfoToDto(ProductInformation domain) {
        if (domain == null) {
            return null;
        }
        ProdInfo dto = new ProdInfo();
        dto.setProdKey(domain.prodKey());
        dto.setModuleKeys(domain.moduleKeys());
        dto.setAgrmnts(mapList(domain.agrmnts(), RaccordoPartitarioContiRaccordoCommonMapper::mapProdInfoAgreementToDto));
        dto.setProdExtKeys(domain.prodExtKeys());
        dto.setProdAttrs(mapList(domain.prodAttrs(), RaccordoPartitarioContiRaccordoCommonMapper::mapProdInfoProdAttrToDto));
        return dto;
    }

    public static StatusInformation mapStatusInfoRPToDomain(StatusInfoRP dto) {
        if (dto == null) {
            return null;
        }
        return new StatusInformation(
                dto.getTsStatus(),
                mapStatusInfoRPStatusToDomain(dto.getStatus()),
                dto.getSStatus());
    }

    public static StatusInfoRP mapStatusInfoRPToDto(StatusInformation domain) {
        if (domain == null) {
            return null;
        }
        StatusInfoRP dto = new StatusInfoRP();
        dto.setTsStatus(domain.tsStatus());
        dto.setStatus(mapStatusInfoRPStatusToDto(domain.status()));
        dto.setSStatus(domain.sStatus());
        return dto;
    }

    public static ServiceAccountData mapSrvActnDataToDomain(SrvActnData dto) {
        if (dto == null) {
            return null;
        }
        return new ServiceAccountData(
                dto.getBankCod(),
                dto.getOfficeCod(),
                dto.getSrvCod(),
                dto.getKey(),
                dto.getExternalKey(),
                dto.getLegacyId(),
                dto.getTsStart(),
                dto.getTsEnd(),
                mapSrvAcntExtKeyToDomain(dto.getSettlementSrvActn()),
                mapStatusInfoRPToDomain(dto.getStatusInfo()),
                mapNdgInfoToDomain(dto.getNdgInfo()),
                mapProdInfoToDomain(dto.getProdInfo()),
                dto.getSrvActnInfo());
    }

    public static SrvActnData mapSrvActnDataToDto(ServiceAccountData domain) {
        if (domain == null) {
            return null;
        }
        SrvActnData dto = new SrvActnData();
        dto.setBankCod(domain.bankCod());
        dto.setOfficeCod(domain.officeCod());
        dto.setSrvCod(domain.srvCod());
        dto.setKey(domain.key());
        dto.setExternalKey(domain.externalKey());
        dto.setLegacyId(domain.legacyId());
        dto.setTsStart(domain.tsStart());
        dto.setTsEnd(domain.tsEnd());
        dto.setSettlementSrvActn(mapSrvAcntExtKeyToDto(domain.settlementSrvActn()));
        dto.setStatusInfo(mapStatusInfoRPToDto(domain.statusInfo()));
        dto.setNdgInfo(mapNdgInfoToDto(domain.ndgInfo()));
        dto.setProdInfo(mapProdInfoToDto(domain.prodInfo()));
        dto.setSrvActnInfo(domain.srvActnInfo());
        return dto;
    }

    public static com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.enums.ServiceAccountStatusCode mapStatusInfoRPStatusToDomain(StatusInfoRPStatus dto) {
        return dto == null ? null : com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.enums.ServiceAccountStatusCode.valueOf(dto.name());
    }

    public static StatusInfoRPStatus mapStatusInfoRPStatusToDto(com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.domain.enums.ServiceAccountStatusCode domain) {
        return domain == null ? null : StatusInfoRPStatus.valueOf(domain.name());
    }

    /**
     * Converte una lista elemento per elemento; una lista null resta null.
     */
    public static <S, T> List<T> mapList(List<S> source, Function<S, T> elementMapper) {
        if (source == null) {
            return null;
        }
        return source.stream().map(elementMapper).toList();
    }
}
