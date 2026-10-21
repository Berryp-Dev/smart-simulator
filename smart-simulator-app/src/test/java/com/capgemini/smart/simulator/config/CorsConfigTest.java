package com.capgemini.smart.simulator.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;
import java.util.Map;

class CorsConfigTest {

    private static final String SIT_ORIGIN = "http://smart-sales.test";
    private static final String OTHER_ORIGIN = "http://altro-sito.test";

    @Test
    void addCorsMappings_withSitOrigin_allowsOnlyThatOrigin() {
        CorsConfiguration configuration = buildConfiguration(List.of(SIT_ORIGIN));

        assertNotNull(configuration.checkOrigin(SIT_ORIGIN));
        assertNull(configuration.checkOrigin(OTHER_ORIGIN));
    }

    @Test
    void addCorsMappings_withWildcard_allowsEveryOrigin() {
        CorsConfiguration configuration = buildConfiguration(List.of("*"));

        assertNotNull(configuration.checkOrigin(SIT_ORIGIN));
        assertNotNull(configuration.checkOrigin(OTHER_ORIGIN));
    }

    @Test
    void addCorsMappings_allowsAllMethodsAndHeadersWithoutCredentialsAndMaxAge3600() {
        CorsConfiguration configuration = buildConfiguration(List.of(SIT_ORIGIN));

        assertEquals(List.of("*"), configuration.getAllowedMethods());
        assertEquals(List.of("*"), configuration.getAllowedHeaders());
        assertFalse(Boolean.TRUE.equals(configuration.getAllowCredentials()));
        assertEquals(3600L, configuration.getMaxAge());
    }

    private CorsConfiguration buildConfiguration(List<String> origins) {
        CorsProperties properties = new CorsProperties();
        properties.setAllowedOrigins(origins);
        CorsConfig corsConfig = new CorsConfig();
        ReflectionTestUtils.setField(corsConfig, "corsProperties", properties);
        TestCorsRegistry registry = new TestCorsRegistry();

        corsConfig.addCorsMappings(registry);

        return registry.exposeConfigurations().get("/**");
    }

    /** Rende leggibili le configurazioni registrate (il metodo di CorsRegistry e' protected). */
    private static class TestCorsRegistry extends CorsRegistry {
        Map<String, CorsConfiguration> exposeConfigurations() {
            return getCorsConfigurations();
        }
    }
}
