package com.capgemini.smart.simulator.account.raccordopartitario.sales.create.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CreateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CreateDtoRequestPayload;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CreateDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvAcntKey;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvActnData;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.StatusInfoRP;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreation;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model.AccountRaccordoCreationResult;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * Converte tra i modelli generati dallo YAML e il dominio della creazione.
 * Nessuna validazione: i campi mancanti restano assenti.
 */
@Component
public class AccountRaccordoCreateMapper {

    /** Legge {@code payload.srvActnData.bankCod/srvCod}, null-safe a ogni livello. */
    public AccountRaccordoCreation toDomain(CreateDtoRequest request) {
        CreateDtoRequestPayload payload = request == null ? null : request.getPayload();
        SrvActnData srvActnData = payload == null ? null : payload.getSrvActnData();
        if (srvActnData == null) {
            return new AccountRaccordoCreation(null, null);
        }
        return new AccountRaccordoCreation(srvActnData.getBankCod(), srvActnData.getSrvCod());
    }

    /** La response generata non dichiara {@code header}: non se ne gestisce alcuno. */
    public CreateDtoResponse toResponse(AccountRaccordoCreationResult result) {
        SrvAcntKey key = new SrvAcntKey()
                .bankCod(result.bankCode())
                .srvCod(result.serviceCode())
                .key(result.serviceAccountKey());
        StatusInfoRP statusInfo = new StatusInfoRP()
                .status(StatusInfoRP.StatusEnum.fromValue(result.status()))
                .tsStatus(toUtcMillis(result));
        // il generato espone sStatus solo con il setter setsStatus
        statusInfo.setsStatus(result.statusDescription());
        return new CreateDtoResponse().srvAcntKey(key).statusInfo(statusInfo);
    }

    private OffsetDateTime toUtcMillis(AccountRaccordoCreationResult result) {
        return OffsetDateTime.ofInstant(result.statusTimestamp().truncatedTo(ChronoUnit.MILLIS), ZoneOffset.UTC);
    }
}
