package com.capgemini.smart.simulator.core.logging;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.capgemini.smart.simulator.core.mq.MqPublisherService;
import com.capgemini.smart.simulator.core.rest.GlobalExceptionHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class RestLoggingFilterTest {

    private static final String CONTEXT = "/smart-simulator";
    private static final byte[] PDF_BYTES = new byte[]{'%', 'P', 'D', 'F', 0, 1, 2, (byte) 0xFF, (byte) 0xFE};

    @Mock
    private JmsTemplate jmsTemplate;

    private MockMvc mockMvc;
    private LogCapture capture;
    private LogCapture mqCapture;

    @BeforeEach
    void setUp() {
        MqPublisherService mqPublisherService = new MqPublisherService();
        ReflectionTestUtils.setField(mqPublisherService, "jmsTemplate", jmsTemplate);
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController(mqPublisherService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilters(new RestLoggingFilter())
                .build();
        capture = new LogCapture(RestLoggingFilter.class);
        mqCapture = new LogCapture(MqPublisherService.class);
    }

    @AfterEach
    void tearDown() {
        capture.close();
        mqCapture.close();
        MDC.clear();
    }

    @Test
    void postJson_logsQueryAndBodyAndControllerReceivesIntactBody() throws Exception {
        mockMvc.perform(post(CONTEXT + "/test/echo").contextPath(CONTEXT).queryParam("productCode", "CA001")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"perché\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"perché\"}"));

        List<String> messages = messages(capture);
        assertEquals(2, messages.size());
        assertTrue(messages.get(0).startsWith("Richiesta REST: POST"));
        assertTrue(messages.get(0).contains("/smart-simulator/test/echo?productCode=CA001"));
        assertTrue(messages.get(0).contains("application/json"));
        assertTrue(messages.get(0).contains("{\"name\":\"perché\"}"));
        assertTrue(messages.get(1).startsWith("Risposta REST: POST"));
        assertTrue(messages.get(1).contains("status=200"));
        assertTrue(messages.get(1).contains("{\"name\":\"perché\"}"));
    }

    @Test
    void malformedJson_isLoggedAndResponseStays400WithApiError() throws Exception {
        mockMvc.perform(post(CONTEXT + "/test/echo").contextPath(CONTEXT)
                        .contentType(MediaType.APPLICATION_JSON).content("{non json"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Corpo della richiesta non valido")));

        List<String> messages = messages(capture);
        assertTrue(messages.get(0).contains("{non json"));
        assertTrue(messages.get(1).contains("status=400"));
        assertTrue(messages.get(1).contains("Corpo della richiesta non valido"));
    }

    @Test
    void emptyBody_isIndicatedExplicitly() throws Exception {
        mockMvc.perform(get(CONTEXT + "/test/text").contextPath(CONTEXT)).andExpect(status().isOk());

        assertTrue(messages(capture).get(0).contains("body=(vuoto)"));
    }

    @Test
    void pdfResponse_logsOnlyMetadataAndClientReceivesIntactBytes() throws Exception {
        byte[] received = mockMvc.perform(get(CONTEXT + "/test/pdf").contextPath(CONTEXT))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        assertArrayEquals(PDF_BYTES, received);
        String responseLog = messages(capture).get(1);
        assertTrue(responseLog.startsWith("Risposta REST: GET"));
        assertTrue(responseLog.contains("status=200"));
        assertTrue(responseLog.contains("application/pdf"));
        assertTrue(responseLog.contains("lunghezza=" + PDF_BYTES.length + " byte"));
        assertFalse(responseLog.contains("%PDF"));
    }

    @Test
    void excludedRequests_produceNoLogLines() throws Exception {
        mockMvc.perform(options(CONTEXT + "/test/echo").contextPath(CONTEXT));
        mockMvc.perform(get(CONTEXT + "/swagger-ui/index.html").contextPath(CONTEXT));
        mockMvc.perform(get(CONTEXT + "/swagger-ui.html").contextPath(CONTEXT));
        mockMvc.perform(get(CONTEXT + "/v3/api-docs/swagger-config").contextPath(CONTEXT));
        mockMvc.perform(get(CONTEXT + "/actuator/health").contextPath(CONTEXT));

        assertTrue(capture.getEvents().isEmpty());
    }

    @Test
    void requestId_isPresentInRestAndMqLinesAndRemovedAtEnd() throws Exception {
        mockMvc.perform(post(CONTEXT + "/test/mq").contextPath(CONTEXT)
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"x\"}")).andExpect(status().isOk());

        String requestId = capture.getEvents().get(0).getMDCPropertyMap().get(RestLoggingFilter.REQUEST_ID_KEY);
        assertNotNull(requestId);
        assertEquals(8, requestId.length());
        assertEquals(requestId, capture.getEvents().get(1).getMDCPropertyMap().get(RestLoggingFilter.REQUEST_ID_KEY));
        assertEquals(2, mqCapture.getEvents().size());
        mqCapture.getEvents().forEach(event ->
                assertEquals(requestId, event.getMDCPropertyMap().get(RestLoggingFilter.REQUEST_ID_KEY)));
        assertNull(MDC.get(RestLoggingFilter.REQUEST_ID_KEY));
    }

    @Test
    void exceptionPropagatingFromChain_isLogged500RethrownAndRequestIdRemoved() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", CONTEXT + "/test/boom");
        request.setContextPath(CONTEXT);
        FilterChain failingChain = (req, res) -> {
            throw new ServletException("non gestita");
        };

        assertThrows(ServletException.class,
                () -> new RestLoggingFilter().doFilter(request, new MockHttpServletResponse(), failingChain));

        List<String> messages = messages(capture);
        assertTrue(messages.get(1).contains("status=500"));
        assertNull(MDC.get(RestLoggingFilter.REQUEST_ID_KEY));
    }

    private List<String> messages(LogCapture logCapture) {
        return logCapture.getEvents().stream().map(ILoggingEvent::getFormattedMessage).collect(Collectors.toList());
    }

    /** Controller di prova montato sotto il filtro. */
    @RestController
    @RequestMapping("/test")
    static class TestController {

        private final MqPublisherService mqPublisherService;

        TestController(MqPublisherService mqPublisherService) {
            this.mqPublisherService = mqPublisherService;
        }

        record Payload(String name) {
        }

        @PostMapping(value = "/echo", produces = MediaType.APPLICATION_JSON_VALUE)
        Payload echo(@RequestBody Payload payload) {
            return payload;
        }

        @PostMapping(value = "/mq", produces = MediaType.APPLICATION_JSON_VALUE)
        Payload mq(@RequestBody Payload payload) {
            mqPublisherService.sendRawText("DEV.QUEUE.SIMULATOR", "<xml>" + payload.name() + "</xml>");
            return payload;
        }

        @GetMapping(value = "/text", produces = MediaType.TEXT_PLAIN_VALUE)
        String text() {
            return "ciao";
        }

        @GetMapping("/pdf")
        ResponseEntity<byte[]> pdf() {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(PDF_BYTES);
        }
    }
}
