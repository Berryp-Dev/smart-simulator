package com.capgemini.smart.simulator.documentviewer.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Legge da application.yml (importato da document-viewer-config.yml) la
 * cartella del classpath del modulo che contiene i PDF statici, relativa alla
 * radice del classpath:
 *
 * <pre>
 * smart-simulator:
 *   document-viewer:
 *     products-folder: pdf/static
 * </pre>
 * <p>
 * Il valore e' obbligatorio: se manca o e' vuoto l'avvio fallisce (fail-fast).
 */
@Validated
@ConfigurationProperties(prefix = "smart-simulator.document-viewer")
public class DocumentViewerProperties {

    @NotBlank
    private String productsFolder;

    public String getProductsFolder() {
        return productsFolder;
    }

    public void setProductsFolder(String productsFolder) {
        this.productsFolder = productsFolder;
    }
}
