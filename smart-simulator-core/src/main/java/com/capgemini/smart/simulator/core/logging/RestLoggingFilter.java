package com.capgemini.smart.simulator.core.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Traccia (INFO) request e response di tutti i servizi REST di tutti i moduli,
 * senza intervenire sui RestResource. Header non loggati. Esclusi: richieste
 * OPTIONS, Swagger UI, /v3/api-docs e /actuator (path confrontati dopo il context-path).
 * <p>
 * Ogni richiesta riceve un {@code requestId} nell'MDC, sempre rimosso a fine
 * richiesta. Il body della richiesta e' letto subito in un array di byte (cosi'
 * e' loggato prima dell'esecuzione) e riesposto tramite
 * {@link CachedBodyHttpServletRequest}. Il body della risposta e' loggato per
 * intero solo se testuale (JSON, XML, text/*); per gli altri tipi (es. il PDF
 * di getStatic) si loggano solo status, tipo, lunghezza e durata.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class RestLoggingFilter extends OncePerRequestFilter {

    /** Chiave MDC dell'identificativo di richiesta (usata in logging.pattern.level). */
    public static final String REQUEST_ID_KEY = "requestId";

    private static final Logger log = LoggerFactory.getLogger(RestLoggingFilter.class);

    private static final int REQUEST_ID_LENGTH = 8;
    private static final String EMPTY_BODY = "(vuoto)";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        String path = resolvePathWithoutContext(request);
        return path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/actuator");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        MDC.put(REQUEST_ID_KEY, UUID.randomUUID().toString().substring(0, REQUEST_ID_LENGTH));
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        long startNanos = System.nanoTime();
        boolean failed = false;
        try {
            byte[] requestBody = request.getInputStream().readAllBytes();
            logRequest(request, requestBody);
            HttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(request, requestBody,
                    resolveRequestCharset(request));
            filterChain.doFilter(cachedRequest, responseWrapper);
        } catch (ServletException | IOException | RuntimeException ex) {
            failed = true;
            throw ex;
        } finally {
            try {
                logResponse(request, responseWrapper, failed, (System.nanoTime() - startNanos) / 1_000_000);
                responseWrapper.copyBodyToResponse();
            } finally {
                MDC.remove(REQUEST_ID_KEY);
            }
        }
    }

    private void logRequest(HttpServletRequest request, byte[] body) {
        String bodyText = body.length == 0 ? EMPTY_BODY : new String(body, resolveRequestCharset(request));
        log.info("Richiesta REST: {} {}, Content-Type={}, body={}", request.getMethod(), resolveUriWithQuery(request),
                request.getContentType(), bodyText);
    }

    private void logResponse(HttpServletRequest request, ContentCachingResponseWrapper response, boolean failed,
                             long durationMillis) {
        int status = failed ? HttpServletResponse.SC_INTERNAL_SERVER_ERROR : response.getStatus();
        String contentType = response.getContentType();
        byte[] body = response.getContentAsByteArray();
        if (isTextual(contentType)) {
            String bodyText = body.length == 0 ? EMPTY_BODY : new String(body, resolveResponseCharset(contentType));
            log.info("Risposta REST: {} {}, status={}, Content-Type={}, durata={} ms, body={}", request.getMethod(),
                    resolveUriWithQuery(request), status, contentType, durationMillis, bodyText);
        } else {
            log.info("Risposta REST: {} {}, status={}, Content-Type={}, lunghezza={} byte, durata={} ms "
                            + "(contenuto non loggato)", request.getMethod(), resolveUriWithQuery(request), status,
                    contentType, body.length, durationMillis);
        }
    }

    /** Testuale: JSON, XML o text/*. Tipo assente o ignoto: non testuale. */
    private boolean isTextual(String contentType) {
        if (contentType == null) {
            return false;
        }
        try {
            MediaType mediaType = MediaType.parseMediaType(contentType);
            String subtype = mediaType.getSubtype();
            return "text".equals(mediaType.getType())
                    || "json".equals(subtype) || subtype.endsWith("+json")
                    || "xml".equals(subtype) || subtype.endsWith("+xml");
        } catch (RuntimeException ex) {
            return false;
        }
    }

    private String resolvePathWithoutContext(HttpServletRequest request) {
        return request.getRequestURI().substring(request.getContextPath().length());
    }

    private String resolveUriWithQuery(HttpServletRequest request) {
        String query = request.getQueryString();
        return query == null ? request.getRequestURI() : request.getRequestURI() + "?" + query;
    }

    private Charset resolveRequestCharset(HttpServletRequest request) {
        return resolveCharset(request.getCharacterEncoding());
    }

    /** Charset dichiarato nel Content-Type della risposta; in assenza UTF-8 (non il default servlet ISO-8859-1). */
    private Charset resolveResponseCharset(String contentType) {
        Charset charset = MediaType.parseMediaType(contentType).getCharset();
        return charset == null ? StandardCharsets.UTF_8 : charset;
    }

    private Charset resolveCharset(String encoding) {
        if (encoding == null) {
            return StandardCharsets.UTF_8;
        }
        try {
            return Charset.forName(encoding);
        } catch (RuntimeException ex) {
            return StandardCharsets.UTF_8;
        }
    }
}
