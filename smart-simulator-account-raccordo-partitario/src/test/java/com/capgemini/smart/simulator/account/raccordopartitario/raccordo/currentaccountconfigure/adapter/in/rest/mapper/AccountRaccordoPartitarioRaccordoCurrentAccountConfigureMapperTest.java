package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.dto.CurrentAccountsConfigureRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.dto.enums.Currency;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.dto.model.CurrentAccountsSrvActnInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.domain.enums.CurrencyCode;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.domain.model.CurrentAccountConfiguration;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.domain.model.component.CurrentAccountServiceInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.support.SamplePopulator;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        CurrentAccountsConfigureRequest dto = SamplePopulator.populate(CurrentAccountsConfigureRequest.class);

        CurrentAccountConfiguration domain = AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        CurrentAccountConfiguration domain = AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapper.mapToDomain(new CurrentAccountsConfigureRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapper.mapToDomain(null));
    }

    @Test
    void Currency_allConstants_haveSameNameInDomainEnum() {
        Set<String> dtoNames = Arrays.stream(Currency.values()).map(Enum::name).collect(Collectors.toSet());
        Set<String> domainNames = Arrays.stream(CurrencyCode.values()).map(Enum::name).collect(Collectors.toSet());

        assertEquals(dtoNames, domainNames);
        for (Currency constant : Currency.values()) {
            assertEquals(constant.name(), AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapper.mapCurrencyToDomain(constant).name());
        }
        assertNull(AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapper.mapCurrencyToDomain(null));
    }

    @Test
    void mapCurrentAccountsSrvActnInfoToDomain_populatedChild_copiesParentFieldsToo() {
        CurrentAccountsSrvActnInfo dto = SamplePopulator.populate(CurrentAccountsSrvActnInfo.class);

        CurrentAccountServiceInfo domain = AccountRaccordoPartitarioRaccordoCurrentAccountConfigureMapper.mapCurrentAccountsSrvActnInfoToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }
}
