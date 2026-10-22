package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.CanBeCreatedRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.CanBeCreatedResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.enums.SimpleProductNdgNature;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.model.CanBeCreatedRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.model.CanBeCreatedResult;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.model.ExternalProdInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.model.ExternalProdInfoProdAttr;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.model.SimpleProduct;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.model.SimpleProductNdgInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.ServiceAccountCreationCheck;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.ServiceAccountCreationCheckResult;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component.CreationCheckOutcome;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component.ExternalProductAttribute;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component.ExternalProductInformation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component.PreCreationNdgInformation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component.ServiceAccountPreCreationData;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.common.adapter.in.rest.mapper.AccountRaccordoPartitarioRaccordoCommonMapper;

/**
 * Mapping DTO <-> dominio dell'API canBeCreated.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class AccountRaccordoPartitarioRaccordoCanBeCreatedMapper {

    private AccountRaccordoPartitarioRaccordoCanBeCreatedMapper() {
    }

    public static ServiceAccountCreationCheck mapToDomain(CanBeCreatedRequest dto) {
        if (dto == null) {
            return null;
        }
        CanBeCreatedRequestPayload payload = dto.getPayload();
        return new ServiceAccountCreationCheck(
                AccountRaccordoPartitarioRaccordoCommonMapper.mapHeaderToDomain(dto.getHeader()),
                payload == null ? null : payload.getAdditionalUserData(),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapSrvAcntExtKeyToDomain(payload == null ? null : payload.getSettlementSrvActn()),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapList(payload == null ? null : payload.getSrvActnPreDatas(), AccountRaccordoPartitarioRaccordoCanBeCreatedMapper::mapSimpleProductToDomain));
    }

    public static CanBeCreatedResponse mapToDtoResp(ServiceAccountCreationCheckResult domain) {
        if (domain == null) {
            return null;
        }
        CanBeCreatedResponse dto = new CanBeCreatedResponse();
        dto.setResults(AccountRaccordoPartitarioRaccordoCommonMapper.mapList(domain.results(), AccountRaccordoPartitarioRaccordoCanBeCreatedMapper::mapCanBeCreatedResultToDto));
        return dto;
    }

    public static ServiceAccountPreCreationData mapSimpleProductToDomain(SimpleProduct dto) {
        if (dto == null) {
            return null;
        }
        return new ServiceAccountPreCreationData(
                dto.getBankCod(),
                dto.getSrvCod(),
                dto.getOfficeCod(),
                mapSimpleProductNdgInfoToDomain(dto.getNdgInfo()),
                mapExternalProdInfoToDomain(dto.getExternalProdInfo()));
    }

    public static SimpleProduct mapSimpleProductToDto(ServiceAccountPreCreationData domain) {
        if (domain == null) {
            return null;
        }
        SimpleProduct dto = new SimpleProduct();
        dto.setBankCod(domain.bankCod());
        dto.setSrvCod(domain.srvCod());
        dto.setOfficeCod(domain.officeCod());
        dto.setNdgInfo(mapSimpleProductNdgInfoToDto(domain.ndgInfo()));
        dto.setExternalProdInfo(mapExternalProdInfoToDto(domain.externalProdInfo()));
        return dto;
    }

    public static PreCreationNdgInformation mapSimpleProductNdgInfoToDomain(SimpleProductNdgInfo dto) {
        if (dto == null) {
            return null;
        }
        return new PreCreationNdgInformation(
                dto.getCntrNdgKey(),
                dto.getCntrNdgType(),
                dto.getCntrSignNdgKeys(),
                mapSimpleProductNdgNatureToDomain(dto.getNdgNature()));
    }

    public static SimpleProductNdgInfo mapSimpleProductNdgInfoToDto(PreCreationNdgInformation domain) {
        if (domain == null) {
            return null;
        }
        SimpleProductNdgInfo dto = new SimpleProductNdgInfo();
        dto.setCntrNdgKey(domain.cntrNdgKey());
        dto.setCntrNdgType(domain.cntrNdgType());
        dto.setCntrSignNdgKeys(domain.cntrSignNdgKeys());
        dto.setNdgNature(mapSimpleProductNdgNatureToDto(domain.ndgNature()));
        return dto;
    }

    public static ExternalProductInformation mapExternalProdInfoToDomain(ExternalProdInfo dto) {
        if (dto == null) {
            return null;
        }
        return new ExternalProductInformation(
                dto.getProdExtKey(),
                AccountRaccordoPartitarioRaccordoCommonMapper.mapList(dto.getProdAttrs(), AccountRaccordoPartitarioRaccordoCanBeCreatedMapper::mapExternalProdInfoProdAttrToDomain));
    }

    public static ExternalProdInfo mapExternalProdInfoToDto(ExternalProductInformation domain) {
        if (domain == null) {
            return null;
        }
        ExternalProdInfo dto = new ExternalProdInfo();
        dto.setProdExtKey(domain.prodExtKey());
        dto.setProdAttrs(AccountRaccordoPartitarioRaccordoCommonMapper.mapList(domain.prodAttrs(), AccountRaccordoPartitarioRaccordoCanBeCreatedMapper::mapExternalProdInfoProdAttrToDto));
        return dto;
    }

    public static ExternalProductAttribute mapExternalProdInfoProdAttrToDomain(ExternalProdInfoProdAttr dto) {
        if (dto == null) {
            return null;
        }
        return new ExternalProductAttribute(
                dto.getAttrCod(),
                dto.getAttrVal());
    }

    public static ExternalProdInfoProdAttr mapExternalProdInfoProdAttrToDto(ExternalProductAttribute domain) {
        if (domain == null) {
            return null;
        }
        ExternalProdInfoProdAttr dto = new ExternalProdInfoProdAttr();
        dto.setAttrCod(domain.attrCod());
        dto.setAttrVal(domain.attrVal());
        return dto;
    }

    public static CanBeCreatedResult mapCanBeCreatedResultToDto(CreationCheckOutcome domain) {
        if (domain == null) {
            return null;
        }
        CanBeCreatedResult dto = new CanBeCreatedResult();
        dto.setSrvActnPreData(mapSimpleProductToDto(domain.srvActnPreData()));
        dto.setCanBeCreated(domain.canBeCreated());
        dto.setMotivationCode(domain.motivationCode());
        dto.setMotivationDescription(domain.motivationDescription());
        dto.setAdditionalInfo(AccountRaccordoPartitarioRaccordoCommonMapper.mapList(domain.additionalInfo(), AccountRaccordoPartitarioRaccordoCommonMapper::mapAdditionalAttributeToDto));
        return dto;
    }

    public static com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.enums.NdgNatureCode mapSimpleProductNdgNatureToDomain(SimpleProductNdgNature dto) {
        return dto == null ? null : com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.enums.NdgNatureCode.valueOf(dto.name());
    }

    public static SimpleProductNdgNature mapSimpleProductNdgNatureToDto(com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.enums.NdgNatureCode domain) {
        return domain == null ? null : SimpleProductNdgNature.valueOf(domain.name());
    }
}
