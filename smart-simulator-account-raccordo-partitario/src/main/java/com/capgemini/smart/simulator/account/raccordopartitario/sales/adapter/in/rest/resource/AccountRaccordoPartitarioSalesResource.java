package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.resource;

import com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.exception.NotImplementedOperationException;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.api.SalesApi;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.ActivateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.ActivateDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CanBeCreatedRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CanBeCreatedResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CancelDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CreateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CreateDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.GetAdditionalAttributesDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.GetAdditionalAttributesDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.GetExternalKeyDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.GetExternalKeyDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.GetMainInfoDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.GetMainInfoDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.GetOthSrvActnsDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.GetOthSrvActnsDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.ReserveDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.ReserveDtoResponse;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SetFinalNdgDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SetSettlementServiceAccountKeyDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.adapter.in.rest.mapper.AccountRaccordoCreateMapper;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.create.application.port.in.CreateAccountRaccordoUseCase;
import com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementa l'interfaccia generata {@link SalesApi}: {@code create} è
 * implementata, le altre operazioni rispondono HTTP 501 (non ancora implementate).
 */
@RestController
@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "raccordopartitario_sales.yaml")
public class AccountRaccordoPartitarioSalesResource implements SalesApi {

    private final CreateAccountRaccordoUseCase createAccountRaccordoUseCase;
    private final AccountRaccordoCreateMapper createMapper;

    public AccountRaccordoPartitarioSalesResource(CreateAccountRaccordoUseCase createAccountRaccordoUseCase,
                                                  AccountRaccordoCreateMapper createMapper) {
        this.createAccountRaccordoUseCase = createAccountRaccordoUseCase;
        this.createMapper = createMapper;
    }

    @Override
    public ResponseEntity<ActivateDtoResponse> activate(ActivateDtoRequest activateDtoRequest) {
        throw new NotImplementedOperationException(activateDtoRequest);
    }

    @Override
    public ResponseEntity<CanBeCreatedResponse> canBeCreated(CanBeCreatedRequest canBeCreatedRequest) {
        throw new NotImplementedOperationException(canBeCreatedRequest);
    }

    @Override
    public ResponseEntity<Void> cancel(CancelDtoRequest cancelDtoRequest) {
        throw new NotImplementedOperationException(cancelDtoRequest);
    }

    @Override
    public ResponseEntity<CreateDtoResponse> create(CreateDtoRequest createDtoRequest) {
        CreateDtoResponse response = createMapper.toResponse(
                createAccountRaccordoUseCase.createAccountRaccordo(createMapper.toDomain(createDtoRequest)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<GetAdditionalAttributesDtoResponse> getAdditionalAttributes(
            GetAdditionalAttributesDtoRequest getAdditionalAttributesDtoRequest) {
        throw new NotImplementedOperationException(getAdditionalAttributesDtoRequest);
    }

    @Override
    public ResponseEntity<GetExternalKeyDtoResponse> getExternalKey(GetExternalKeyDtoRequest getExternalKeyDtoRequest) {
        throw new NotImplementedOperationException(getExternalKeyDtoRequest);
    }

    @Override
    public ResponseEntity<GetMainInfoDtoResponse> getMainInfo(GetMainInfoDtoRequest getMainInfoDtoRequest) {
        throw new NotImplementedOperationException(getMainInfoDtoRequest);
    }

    @Override
    public ResponseEntity<GetOthSrvActnsDtoResponse> getOthSrvActns(GetOthSrvActnsDtoRequest getOthSrvActnsDtoRequest) {
        throw new NotImplementedOperationException(getOthSrvActnsDtoRequest);
    }

    @Override
    public ResponseEntity<ReserveDtoResponse> reserve(ReserveDtoRequest reserveDtoRequest) {
        throw new NotImplementedOperationException(reserveDtoRequest);
    }

    @Override
    public ResponseEntity<Void> setFinalNdg(SetFinalNdgDtoRequest setFinalNdgDtoRequest) {
        throw new NotImplementedOperationException(setFinalNdgDtoRequest);
    }

    @Override
    public ResponseEntity<Void> setSettlementServiceAccountKey(
            SetSettlementServiceAccountKeyDtoRequest setSettlementServiceAccountKeyDtoRequest) {
        throw new NotImplementedOperationException(setSettlementServiceAccountKeyDtoRequest);
    }
}
