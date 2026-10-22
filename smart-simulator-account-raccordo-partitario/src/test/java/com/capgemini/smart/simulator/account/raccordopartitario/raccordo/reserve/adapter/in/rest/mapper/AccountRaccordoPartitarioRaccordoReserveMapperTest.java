package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.dto.ReserveDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.dto.ReserveDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.domain.model.ServiceAccountReservation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.domain.model.ServiceAccountReservationResult;
import com.capgemini.smart.simulator.account.raccordopartitario.support.SamplePopulator;
import org.junit.jupiter.api.Test;

class AccountRaccordoPartitarioRaccordoReserveMapperTest {

    @Test
    void mapToDomain_populatedRequest_mapsEveryField() {
        ReserveDtoRequest dto = SamplePopulator.populate(ReserveDtoRequest.class);

        ServiceAccountReservation domain = AccountRaccordoPartitarioRaccordoReserveMapper.mapToDomain(dto);

        SamplePopulator.assertFullyPopulated(domain);
    }

    @Test
    void mapToDomain_emptyRequest_keepsNullsWithoutErrors() {
        ServiceAccountReservation domain = AccountRaccordoPartitarioRaccordoReserveMapper.mapToDomain(new ReserveDtoRequest());

        SamplePopulator.assertAllComponentsNull(domain);
    }

    @Test
    void mapToDomain_nullRequest_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoReserveMapper.mapToDomain(null));
    }

    @Test
    void mapToDtoResp_populatedResult_mapsEveryField() {
        ServiceAccountReservationResult domain = SamplePopulator.populate(ServiceAccountReservationResult.class);

        ReserveDtoResponse dto = AccountRaccordoPartitarioRaccordoReserveMapper.mapToDtoResp(domain);

        SamplePopulator.assertFullyPopulated(dto);
    }

    @Test
    void mapToDtoResp_resultWithNulls_keepsNullsWithoutErrors() {
        ServiceAccountReservationResult domain = SamplePopulator.populateWithNulls(ServiceAccountReservationResult.class);

        ReserveDtoResponse dto = AccountRaccordoPartitarioRaccordoReserveMapper.mapToDtoResp(domain);

        SamplePopulator.assertAllPropertiesNull(dto);
    }

    @Test
    void mapToDtoResp_nullResult_returnsNull() {
        assertNull(AccountRaccordoPartitarioRaccordoReserveMapper.mapToDtoResp(null));
    }
}
