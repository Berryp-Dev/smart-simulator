package com.capgemini.smart.simulator.paperless.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Abilita il binding di {@link PaperlessMqProperties} da application.yml.
 */
@Configuration
@EnableConfigurationProperties(PaperlessMqProperties.class)
public class PaperlessMqConfig {
}
