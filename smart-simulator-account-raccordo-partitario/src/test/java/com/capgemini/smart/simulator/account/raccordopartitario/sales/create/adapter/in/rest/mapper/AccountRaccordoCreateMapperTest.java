package com.capgemini.smart.simulator.account.raccordopartitario.sales.create.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CreateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CreateDtoRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CreateDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvActnData;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.StatusInfoRP;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreation;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreationResult;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AccountRaccordoCreateMapperTest {

    private final AccountRaccordoCreateMapper mapper = new AccountRaccordoCreateMapper();

    @Test
    void toDomainReadsCodesFromPayloadSrvActnData() {
        CreateDtoRequest request = new CreateDtoRequest().payload(new CreateDtoRequestPayload()
                .srvActnData(new SrvActnData().bankCod("01005").srvCod("SRV1")));

        AccountRaccordoCreation creation = mapper.toDomain(request);

        assertEquals("01005", creation.bankCode());
        assertEquals("SRV1", creation.serviceCode());
    }

    @Test
    void toDomainWithMissingCodesKeepsThemNull() {
        CreateDtoRequest request = new CreateDtoRequest().payload(new CreateDtoRequestPayload()
                .srvActnData(new SrvActnData()));

        AccountRaccordoCreation creation = mapper.toDomain(request);

        assertNull(creation.bankCode());
        assertNull(creation.serviceCode());
    }

    @Test
    void toDomainWithNullPayloadKeepsCodesNull() {
        AccountRaccordoCreation creation = mapper.toDomain(new CreateDtoRequest());

        assertNull(creation.bankCode());
        assertNull(creation.serviceCode());
    }

    @Test
    void toDomainWithNullSrvActnDataKeepsCodesNull() {
        AccountRaccordoCreation creation = mapper.toDomain(
                new CreateDtoRequest().payload(new CreateDtoRequestPayload()));

        assertNull(creation.bankCode());
        assertNull(creation.serviceCode());
    }

    @Test
    void toResponseMapsKeyAndStatus() {
        CreateDtoResponse response = mapper.toResponse(result("01005", "SRV1", Instant.parse("2024-01-18T15:04:05Z")));

        assertEquals("01005", response.getSrvAcntKey().getBankCod());
        assertEquals("SRV1", response.getSrvAcntKey().getSrvCod());
        assertEquals("key-1", response.getSrvAcntKey().getKey());
        assertEquals(StatusInfoRP.StatusEnum.CREATED, response.getStatusInfo().getStatus());
        assertEquals("Creato", response.getStatusInfo().getsStatus());
    }

    @Test
    void toResponseWithZeroMillisecondsKeepsInstantInUtc() {
        CreateDtoResponse response = mapper.toResponse(result("01005", "SRV1", Instant.parse("2024-01-18T15:04:05Z")));

        assertEquals(OffsetDateTime.of(2024, 1, 18, 15, 4, 5, 0, ZoneOffset.UTC), response.getStatusInfo().getTsStatus());
    }

    @Test
    void toResponseTruncatesNanosecondsToMillisecondsWithoutRounding() {
        // 123_999_999 ns: arrotondando si arriverebbe a 124 ms
        CreateDtoResponse response = mapper.toResponse(
                result("01005", "SRV1", Instant.parse("2024-01-18T15:04:05.123999999Z")));

        OffsetDateTime timestamp = response.getStatusInfo().getTsStatus();
        assertEquals(OffsetDateTime.of(2024, 1, 18, 15, 4, 5, 123_000_000, ZoneOffset.UTC), timestamp);
        assertEquals(ZoneOffset.UTC, timestamp.getOffset());
    }

    @Test
    void toResponseWithMissingCodesReportsThemMissing() {
        CreateDtoResponse response = mapper.toResponse(result(null, null, Instant.parse("2024-01-18T15:04:05Z")));

        assertNull(response.getSrvAcntKey().getBankCod());
        assertNull(response.getSrvAcntKey().getSrvCod());
    }

    private AccountRaccordoCreationResult result(String bankCode, String serviceCode, Instant timestamp) {
        return new AccountRaccordoCreationResult(bankCode, serviceCode, "key-1", "CREATED", "Creato", timestamp);
    }
}
