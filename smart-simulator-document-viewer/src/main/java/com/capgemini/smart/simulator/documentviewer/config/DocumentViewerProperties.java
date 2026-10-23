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
 *     generated-products-folder: pdf/generated
 *     default-generated-name: GENERATED.PDF
 *     token-decryption-key: 000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f
 * </pre>
 * <p>
 * Tutti i valori sono obbligatori: se uno manca o e' vuoto l'avvio fallisce (fail-fast).
 */
@Validated
@ConfigurationProperties(prefix = "smart-simulator.document-viewer")
public class DocumentViewerProperties {

    @NotBlank
    private String productsFolder;

    @NotBlank
    private String generatedProductsFolder;

    @NotBlank
    private String defaultGeneratedName;

    @NotBlank
    private String tokenDecryptionKey;

    public String getProductsFolder() {
        return productsFolder;
    }

    public void setProductsFolder(String productsFolder) {
        this.productsFolder = productsFolder;
    }

    public String getGeneratedProductsFolder() {
        return generatedProductsFolder;
    }

    public void setGeneratedProductsFolder(String generatedProductsFolder) {
        this.generatedProductsFolder = generatedProductsFolder;
    }

    public String getDefaultGeneratedName() {
        return defaultGeneratedName;
    }

    public void setDefaultGeneratedName(String defaultGeneratedName) {
        this.defaultGeneratedName = defaultGeneratedName;
    }

    public String getTokenDecryptionKey() {
        return tokenDecryptionKey;
    }

    public void setTokenDecryptionKey(String tokenDecryptionKey) {
        this.tokenDecryptionKey = tokenDecryptionKey;
    }
}
