package com.capgemini.smart.simulator.documentviewer.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Abilita il binding di {@link DocumentViewerProperties} da application.yml.
 */
@Configuration
@EnableConfigurationProperties(DocumentViewerProperties.class)
public class DocumentViewerConfig {
}
