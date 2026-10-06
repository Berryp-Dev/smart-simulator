package com.capgemini.smart.simulator.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Legge la mappa dei valori di default per sottosistema da application.yml:
 *
 * <pre>
 * smart-simulator:
 *   defaults:
 *     paperless:
 *       priority: "NORMALE"
 *       canale-default: "WEB"
 * </pre>
 */
@ConfigurationProperties(prefix = "smart-simulator")
public class SubsystemDefaultsProperties {

    /** Mappa: nome sottosistema -> (nome proprieta' -> valore di default) */
    private Map<String, Map<String, Object>> defaults = new HashMap<>();

    public Map<String, Map<String, Object>> getDefaults() {
        return defaults;
    }

    public void setDefaults(Map<String, Map<String, Object>> defaults) {
        this.defaults = defaults;
    }
}
