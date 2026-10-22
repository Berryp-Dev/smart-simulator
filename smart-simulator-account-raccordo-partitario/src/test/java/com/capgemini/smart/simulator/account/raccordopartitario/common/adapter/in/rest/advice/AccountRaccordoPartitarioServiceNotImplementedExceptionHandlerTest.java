package com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.advice;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.advice.AccountRaccordoPartitarioServiceNotImplementedExceptionHandler;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.adapter.in.rest.dto.ActivateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoActivateRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.application.service.ActivateServiceAccountServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.dto.CanBeCreatedRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoCanBeCreatedRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.application.service.CheckServiceAccountCreationServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.adapter.in.rest.dto.CancelDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoCancelRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.application.service.CancelServiceAccountServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.dto.CreateDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoCreateRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.create.application.service.CreateServiceAccountServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.dto.CurrentAccountsConfigureRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoCurrentAccountConfigureRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.currentaccountconfigure.application.service.ConfigureCurrentAccountServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.adapter.in.rest.dto.GetAdditionalAttributesDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoGetAdditionalAttributesRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.application.service.RetrieveServiceAccountAdditionalAttributesServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoGetExternalKeyRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.application.service.RetrieveServiceAccountExternalKeyServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoGetMainInfoRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.application.service.RetrieveServiceAccountMainInfoServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoGetOthSrvActnsRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.application.service.RetrieveOtherServiceAccountsServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.dto.ReserveDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoReserveRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.application.service.ReserveServiceAccountServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.adapter.in.rest.dto.SetFinalNdgDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoSetFinalNdgRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.application.service.UpdateFinalNdgServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.SetSettlementServiceAccountKeyDtoRequest;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.adapter.in.rest.resource.AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyRestResource;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setsettlementserviceaccountkey.application.service.UpdateSettlementServiceAccountKeyServiceImpl;
import com.capgemini.smart.simulator.account.raccordopartitario.support.SamplePopulator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Per ogni endpoint del modulo: la chiamata arriva fino al ServiceImpl e risponde 501 con l'envelope
 * di errore dello YAML (Error501 -> ErrorResponse con mainError.errorMessage).
 */
class AccountRaccordoPartitarioServiceNotImplementedExceptionHandlerTest {

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        AccountRaccordoPartitarioRaccordoCanBeCreatedRestResource canBeCreatedResource = new AccountRaccordoPartitarioRaccordoCanBeCreatedRestResource();
        ReflectionTestUtils.setField(canBeCreatedResource, "checkServiceAccountCreationService", new CheckServiceAccountCreationServiceImpl());
        AccountRaccordoPartitarioRaccordoCreateRestResource createResource = new AccountRaccordoPartitarioRaccordoCreateRestResource();
        ReflectionTestUtils.setField(createResource, "createServiceAccountService", new CreateServiceAccountServiceImpl());
        AccountRaccordoPartitarioRaccordoReserveRestResource reserveResource = new AccountRaccordoPartitarioRaccordoReserveRestResource();
        ReflectionTestUtils.setField(reserveResource, "reserveServiceAccountService", new ReserveServiceAccountServiceImpl());
        AccountRaccordoPartitarioRaccordoActivateRestResource activateResource = new AccountRaccordoPartitarioRaccordoActivateRestResource();
        ReflectionTestUtils.setField(activateResource, "activateServiceAccountService", new ActivateServiceAccountServiceImpl());
        AccountRaccordoPartitarioRaccordoGetMainInfoRestResource getMainInfoResource = new AccountRaccordoPartitarioRaccordoGetMainInfoRestResource();
        ReflectionTestUtils.setField(getMainInfoResource, "retrieveServiceAccountMainInfoService", new RetrieveServiceAccountMainInfoServiceImpl());
        AccountRaccordoPartitarioRaccordoGetExternalKeyRestResource getExternalKeyResource = new AccountRaccordoPartitarioRaccordoGetExternalKeyRestResource();
        ReflectionTestUtils.setField(getExternalKeyResource, "retrieveServiceAccountExternalKeyService", new RetrieveServiceAccountExternalKeyServiceImpl());
        AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyRestResource setSettlementServiceAccountKeyResource = new AccountRaccordoPartitarioRaccordoSetSettlementServiceAccountKeyRestResource();
        ReflectionTestUtils.setField(setSettlementServiceAccountKeyResource, "updateSettlementServiceAccountKeyService", new UpdateSettlementServiceAccountKeyServiceImpl());
        AccountRaccordoPartitarioRaccordoGetOthSrvActnsRestResource getOthSrvActnsResource = new AccountRaccordoPartitarioRaccordoGetOthSrvActnsRestResource();
        ReflectionTestUtils.setField(getOthSrvActnsResource, "retrieveOtherServiceAccountsService", new RetrieveOtherServiceAccountsServiceImpl());
        AccountRaccordoPartitarioRaccordoGetAdditionalAttributesRestResource getAdditionalAttributesResource = new AccountRaccordoPartitarioRaccordoGetAdditionalAttributesRestResource();
        ReflectionTestUtils.setField(getAdditionalAttributesResource, "retrieveServiceAccountAdditionalAttributesService", new RetrieveServiceAccountAdditionalAttributesServiceImpl());
        AccountRaccordoPartitarioRaccordoSetFinalNdgRestResource setFinalNdgResource = new AccountRaccordoPartitarioRaccordoSetFinalNdgRestResource();
        ReflectionTestUtils.setField(setFinalNdgResource, "updateFinalNdgService", new UpdateFinalNdgServiceImpl());
        AccountRaccordoPartitarioRaccordoCancelRestResource cancelResource = new AccountRaccordoPartitarioRaccordoCancelRestResource();
        ReflectionTestUtils.setField(cancelResource, "cancelServiceAccountService", new CancelServiceAccountServiceImpl());
        AccountRaccordoPartitarioRaccordoCurrentAccountConfigureRestResource currentAccountConfigureResource = new AccountRaccordoPartitarioRaccordoCurrentAccountConfigureRestResource();
        ReflectionTestUtils.setField(currentAccountConfigureResource, "configureCurrentAccountService", new ConfigureCurrentAccountServiceImpl());
        mockMvc = MockMvcBuilders.standaloneSetup(canBeCreatedResource, createResource, reserveResource, activateResource, getMainInfoResource, getExternalKeyResource, setSettlementServiceAccountKeyResource, getOthSrvActnsResource, getAdditionalAttributesResource, setFinalNdgResource, cancelResource, currentAccountConfigureResource)
                .setControllerAdvice(new AccountRaccordoPartitarioServiceNotImplementedExceptionHandler())
                .build();
    }

    static Stream<Arguments> endpoints() {
        return Stream.of(
                Arguments.of("canBeCreated", "/smartcatalog/raccordo/can-be-created", CanBeCreatedRequest.class),
                Arguments.of("create", "/smartengine/raccordo/create", CreateDtoRequest.class),
                Arguments.of("reserve", "/smartengine/raccordo/reserve", ReserveDtoRequest.class),
                Arguments.of("activate", "/smartengine/raccordo/activate", ActivateDtoRequest.class),
                Arguments.of("getMainInfo", "/smartengine/raccordo/getmaininfo", GetMainInfoDtoRequest.class),
                Arguments.of("getExternalKey", "/smartengine/raccordo/getexternalkey", GetExternalKeyDtoRequest.class),
                Arguments.of("setSettlementServiceAccountKey", "/smartengine/raccordo/setsettlementserviceaccountkey", SetSettlementServiceAccountKeyDtoRequest.class),
                Arguments.of("getOthSrvActns", "/smartengine/raccordo/getothsrvactns", GetOthSrvActnsDtoRequest.class),
                Arguments.of("getAdditionalAttributes", "/smartengine/raccordo/getadditionalattributes", GetAdditionalAttributesDtoRequest.class),
                Arguments.of("setFinalNdg", "/smartengine/raccordo/setfinalndg", SetFinalNdgDtoRequest.class),
                Arguments.of("cancel", "/smartengine/raccordo/cancel", CancelDtoRequest.class),
                Arguments.of("CurrentAccountConfigure", "/ui-app/raccordo/sales/current-accounts/configure", CurrentAccountsConfigureRequest.class));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("endpoints")
    void endpoint_emptyJsonBody_returns501WithYamlEnvelope(String operationId, String path, Class<?> requestType) throws Exception {
        mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotImplemented())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mainError.errorMessage").value("Servizio non implementato: " + operationId))
                .andExpect(jsonPath("$.mainError.errorCode").doesNotExist())
                .andExpect(jsonPath("$.mainError.technicalDescription").doesNotExist())
                .andExpect(jsonPath("$.errorList").doesNotExist());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("endpoints")
    void endpoint_fullyPopulatedBody_returns501WithYamlEnvelope(String operationId, String path, Class<?> requestType) throws Exception {
        String body = objectMapper.writeValueAsString(SamplePopulator.populate(requestType));

        mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isNotImplemented())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mainError.errorMessage").value(startsWith("Servizio non implementato")));
    }

    @Test
    void endpoints_count_matchesYamlOperations() {
        org.junit.jupiter.api.Assertions.assertEquals(12, endpoints().count());
    }
}
