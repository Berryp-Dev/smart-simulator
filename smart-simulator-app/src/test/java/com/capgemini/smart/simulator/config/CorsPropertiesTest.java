package com.capgemini.smart.simulator.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

class CorsPropertiesTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(PropertiesConfig.class);

    @Test
    void startup_failsWhenAllowedOriginsMissing() {
        contextRunner.run(context -> assertThat(context).hasFailed());
    }

    @Test
    void startup_failsWhenAllowedOriginsEmpty() {
        contextRunner.withPropertyValues("smart-simulator.cors.allowed-origins=")
                .run(context -> assertThat(context).hasFailed());
    }

    @Test
    void startup_succeedsWithOrigins() {
        contextRunner.withPropertyValues("smart-simulator.cors.allowed-origins[0]=http://smart-sales.test")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertEquals(List.of("http://smart-sales.test"),
                            context.getBean(CorsProperties.class).getAllowedOrigins());
                });
    }

    @Configuration
    @EnableConfigurationProperties(CorsProperties.class)
    static class PropertiesConfig {
    }
}
