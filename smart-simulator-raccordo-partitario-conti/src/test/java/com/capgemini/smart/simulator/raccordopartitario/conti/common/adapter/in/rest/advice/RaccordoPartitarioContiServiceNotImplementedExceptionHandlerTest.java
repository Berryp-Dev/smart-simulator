package com.capgemini.smart.simulator.raccordopartitario.conti.common.adapter.in.rest.advice;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.adapter.in.rest.advice.RaccordoPartitarioContiServiceNotImplementedExceptionHandler;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.dto.ActivateDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoActivateRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.application.service.ActivateServiceAccountServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.dto.CanBeCreatedRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoCanBeCreatedRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.canbecreated.application.service.CheckServiceAccountCreationServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.adapter.in.rest.dto.CancelDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoCancelRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.cancel.application.service.CancelServiceAccountServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.dto.CreateDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoCreateRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.application.service.CreateServiceAccountServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.dto.CurrentAccountsConfigureRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoCurrentAccountConfigureRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.currentaccountconfigure.application.service.ConfigureCurrentAccountServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.dto.GetAdditionalAttributesDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoGetAdditionalAttributesRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.application.service.RetrieveServiceAccountAdditionalAttributesServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.dto.GetExternalKeyDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoGetExternalKeyRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.application.service.RetrieveServiceAccountExternalKeyServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.dto.GetMainInfoDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoGetMainInfoRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.application.service.RetrieveServiceAccountMainInfoServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.dto.GetOthSrvActnsDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoGetOthSrvActnsRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getothsrvactns.application.service.RetrieveOtherServiceAccountsServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.dto.ReserveDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoReserveRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.application.service.ReserveServiceAccountServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.dto.SetFinalNdgDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoSetFinalNdgRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.application.service.UpdateFinalNdgServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.dto.SetSettlementServiceAccountKeyDtoRequest;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.adapter.in.rest.resource.RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyRestResource;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.application.service.UpdateSettlementServiceAccountKeyServiceImpl;
import com.capgemini.smart.simulator.raccordopartitario.conti.support.SamplePopulator;
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
class RaccordoPartitarioContiServiceNotImplementedExceptionHandlerTest {

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RaccordoPartitarioContiRaccordoCanBeCreatedRestResource canBeCreatedResource = new RaccordoPartitarioContiRaccordoCanBeCreatedRestResource();
        ReflectionTestUtils.setField(canBeCreatedResource, "checkServiceAccountCreationService", new CheckServiceAccountCreationServiceImpl());
        RaccordoPartitarioContiRaccordoCreateRestResource createResource = new RaccordoPartitarioContiRaccordoCreateRestResource();
        ReflectionTestUtils.setField(createResource, "createServiceAccountService", new CreateServiceAccountServiceImpl());
        RaccordoPartitarioContiRaccordoReserveRestResource reserveResource = new RaccordoPartitarioContiRaccordoReserveRestResource();
        ReflectionTestUtils.setField(reserveResource, "reserveServiceAccountService", new ReserveServiceAccountServiceImpl());
        RaccordoPartitarioContiRaccordoActivateRestResource activateResource = new RaccordoPartitarioContiRaccordoActivateRestResource();
        ReflectionTestUtils.setField(activateResource, "activateServiceAccountService", new ActivateServiceAccountServiceImpl());
        RaccordoPartitarioContiRaccordoGetMainInfoRestResource getMainInfoResource = new RaccordoPartitarioContiRaccordoGetMainInfoRestResource();
        ReflectionTestUtils.setField(getMainInfoResource, "retrieveServiceAccountMainInfoService", new RetrieveServiceAccountMainInfoServiceImpl());
        RaccordoPartitarioContiRaccordoGetExternalKeyRestResource getExternalKeyResource = new RaccordoPartitarioContiRaccordoGetExternalKeyRestResource();
        ReflectionTestUtils.setField(getExternalKeyResource, "retrieveServiceAccountExternalKeyService", new RetrieveServiceAccountExternalKeyServiceImpl());
        RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyRestResource setSettlementServiceAccountKeyResource = new RaccordoPartitarioContiRaccordoSetSettlementServiceAccountKeyRestResource();
        ReflectionTestUtils.setField(setSettlementServiceAccountKeyResource, "updateSettlementServiceAccountKeyService", new UpdateSettlementServiceAccountKeyServiceImpl());
        RaccordoPartitarioContiRaccordoGetOthSrvActnsRestResource getOthSrvActnsResource = new RaccordoPartitarioContiRaccordoGetOthSrvActnsRestResource();
        ReflectionTestUtils.setField(getOthSrvActnsResource, "retrieveOtherServiceAccountsService", new RetrieveOtherServiceAccountsServiceImpl());
        RaccordoPartitarioContiRaccordoGetAdditionalAttributesRestResource getAdditionalAttributesResource = new RaccordoPartitarioContiRaccordoGetAdditionalAttributesRestResource();
        ReflectionTestUtils.setField(getAdditionalAttributesResource, "retrieveServiceAccountAdditionalAttributesService", new RetrieveServiceAccountAdditionalAttributesServiceImpl());
        RaccordoPartitarioContiRaccordoSetFinalNdgRestResource setFinalNdgResource = new RaccordoPartitarioContiRaccordoSetFinalNdgRestResource();
        ReflectionTestUtils.setField(setFinalNdgResource, "updateFinalNdgService", new UpdateFinalNdgServiceImpl());
        RaccordoPartitarioContiRaccordoCancelRestResource cancelResource = new RaccordoPartitarioContiRaccordoCancelRestResource();
        ReflectionTestUtils.setField(cancelResource, "cancelServiceAccountService", new CancelServiceAccountServiceImpl());
        RaccordoPartitarioContiRaccordoCurrentAccountConfigureRestResource currentAccountConfigureResource = new RaccordoPartitarioContiRaccordoCurrentAccountConfigureRestResource();
        ReflectionTestUtils.setField(currentAccountConfigureResource, "configureCurrentAccountService", new ConfigureCurrentAccountServiceImpl());
        mockMvc = MockMvcBuilders.standaloneSetup(canBeCreatedResource, createResource, reserveResource, activateResource, getMainInfoResource, getExternalKeyResource, setSettlementServiceAccountKeyResource, getOthSrvActnsResource, getAdditionalAttributesResource, setFinalNdgResource, cancelResource, currentAccountConfigureResource)
                .setControllerAdvice(new RaccordoPartitarioContiServiceNotImplementedExceptionHandler())
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
