package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.mapper.RaccordoPartitarioContiRaccordoCommonMapper;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.dto.CurrentAccountsConfigureRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.dto.enums.Currency;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.dto.model.CurrentAccountsSrvActnInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.dto.model.RapportoCollegato;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.model.CurrentAccountConfiguration;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.model.component.CurrentAccountServiceInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.model.component.LinkedAccountRelationship;

/**
 * Mapping DTO <-> dominio dell'API CurrentAccountConfigure.
 * Null-safe e senza validazione: un campo assente resta null. Gli enum sono convertiti
 * con valueOf sul nome della costante (mai switch).
 */
public final class RaccordoPartitarioContiRaccordoCurrentAccountConfigureMapper {

    private RaccordoPartitarioContiRaccordoCurrentAccountConfigureMapper() {
    }

    public static CurrentAccountConfiguration mapToDomain(CurrentAccountsConfigureRequest dto) {
        if (dto == null) {
            return null;
        }
        return new CurrentAccountConfiguration(
                RaccordoPartitarioContiRaccordoCommonMapper.mapSrvAcntKeyToDomain(dto.getSrvAcntKey()),
                mapCurrentAccountsSrvActnInfoToDomain(dto.getSrvActnInfo()));
    }

    public static CurrentAccountServiceInfo mapCurrentAccountsSrvActnInfoToDomain(CurrentAccountsSrvActnInfo dto) {
        if (dto == null) {
            return null;
        }
        return new CurrentAccountServiceInfo(
                dto.getBranchCode(),
                dto.getOperationalProduct(),
                dto.getCommercialProduct(),
                mapCurrencyToDomain(dto.getCurrency()),
                dto.getOrigination(),
                dto.getNatureAndScopeCod(),
                dto.getRiskCode(),
                dto.getOffer(),
                dto.getAgreement(),
                RaccordoPartitarioContiRaccordoCommonMapper.mapList(dto.getLinkedAccount(), RaccordoPartitarioContiRaccordoCurrentAccountConfigureMapper::mapRapportoCollegatoToDomain),
                dto.getLinkedNdg(),
                dto.getLinkedNdgRelationType(),
                dto.getAccountExpiryDate(),
                dto.getAnatocismo(),
                dto.getSettlementFrequency(),
                dto.getSettlementDate(),
                dto.getLinkedCredit(),
                dto.getFirstDepositAmount(),
                dto.getOmitCheckbook());
    }

    public static LinkedAccountRelationship mapRapportoCollegatoToDomain(RapportoCollegato dto) {
        if (dto == null) {
            return null;
        }
        return new LinkedAccountRelationship(
                dto.getRelationshipCod(),
                dto.getOfficeCod(),
                dto.getAccountKey());
    }

    public static com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.enums.CurrencyCode mapCurrencyToDomain(Currency dto) {
        return dto == null ? null : com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.domain.enums.CurrencyCode.valueOf(dto.name());
    }
}
