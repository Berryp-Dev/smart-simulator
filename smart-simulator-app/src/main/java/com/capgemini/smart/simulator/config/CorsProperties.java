package com.capgemini.smart.simulator.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Legge dagli application-&lt;env&gt;.yml la lista degli origin ammessi dal
 * CORS (il valore {@code *} ammette tutti gli origin):
 *
 * <pre>
 * smart-simulator:
 *   cors:
 *     allowed-origins:
 *       - http://smart-sales.test
 * </pre>
 * <p>
 * Il valore e' obbligatorio e non vuoto: se manca o e' vuoto l'avvio fallisce
 * (fail-fast).
 */
@Validated
@ConfigurationProperties(prefix = "smart-simulator.cors")
public class CorsProperties {

    @NotEmpty
    private List<@NotBlank String> allowedOrigins;

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
