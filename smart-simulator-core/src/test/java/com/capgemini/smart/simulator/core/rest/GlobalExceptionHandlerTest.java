package com.capgemini.smart.simulator.core.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

class GlobalExceptionHandlerTest {

    private static final String PATH = "/smart-simulator/foo/bar";

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleNoResourceFound_returns404WithApiError() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", PATH);

        ResponseEntity<ApiError> response = handler.handleNoResourceFound(
                new NoResourceFoundException(HttpMethod.GET, "foo/bar"), request);

        assertApiError(response, HttpStatus.NOT_FOUND, "Risorsa non trovata");
    }

    @Test
    void handleMethodNotSupported_returns405WithApiErrorAndAllowHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", PATH);

        ResponseEntity<ApiError> response = handler.handleMethodNotSupported(
                new HttpRequestMethodNotSupportedException("POST", List.of("GET")), request);

        assertApiError(response, HttpStatus.METHOD_NOT_ALLOWED, "Metodo HTTP non supportato");
        assertEquals("GET", response.getHeaders().getFirst(HttpHeaders.ALLOW));
    }

    @Test
    void handleMediaTypeNotSupported_returns415WithApiErrorAndAcceptHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", PATH);
        request.setContentType("text/plain");

        ResponseEntity<ApiError> response = handler.handleMediaTypeNotSupported(
                new HttpMediaTypeNotSupportedException(MediaType.TEXT_PLAIN, List.of(MediaType.APPLICATION_JSON)),
                request);

        assertApiError(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo di contenuto non supportato");
        assertEquals("application/json", response.getHeaders().getFirst(HttpHeaders.ACCEPT));
    }

    @Test
    void handleMediaTypeNotAcceptable_returns406WithApiError() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", PATH);
        request.addHeader(HttpHeaders.ACCEPT, "application/xml");

        ResponseEntity<ApiError> response = handler.handleMediaTypeNotAcceptable(
                new HttpMediaTypeNotAcceptableException(List.of(MediaType.APPLICATION_JSON)), request);

        assertApiError(response, HttpStatus.NOT_ACCEPTABLE, "Formato di risposta non accettabile");
    }

    private void assertApiError(ResponseEntity<ApiError> response, HttpStatus status, String message) {
        assertEquals(status, response.getStatusCode());
        ApiError body = response.getBody();
        assertNotNull(body);
        assertNotNull(body.timestamp());
        assertEquals(status.value(), body.status());
        assertEquals(status.getReasonPhrase(), body.error());
        assertEquals(message, body.message());
        assertEquals(PATH, body.path());
        assertNull(body.errors());
    }
}
