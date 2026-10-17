package com.capgemini.smart.simulator.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Implementazione di default di {@link DefaultsResolver}: legge i valori da
 * {@link SubsystemDefaultsProperties} (application.yml), ignorando le
 * condizioni aggiuntive. E' il punto di estensione naturale quando un
 * sottosistema ha bisogno di default condizionali (es. in base al canale):
 * basta fornire un bean {@code @Primary} o qualificato che implementi
 * {@link DefaultsResolver} nel modulo del sottosistema.
 */
@Component
@EnableConfigurationProperties(SubsystemDefaultsProperties.class)
public class StaticDefaultsResolver implements DefaultsResolver {

    @Autowired
    private SubsystemDefaultsProperties properties;

    @Override
    public Object resolve(String subsystem, String property, Map<String, Object> conditions) {
        Map<String, Object> subsystemDefaults = properties.getDefaults().get(subsystem);
        if (subsystemDefaults == null) {
            return null;
        }
        return subsystemDefaults.get(property);
    }
}
