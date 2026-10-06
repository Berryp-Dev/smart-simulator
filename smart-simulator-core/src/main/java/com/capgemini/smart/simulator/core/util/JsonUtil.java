package com.capgemini.smart.simulator.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Wrapper su Jackson per serializzazione/deserializzazione sicura: gli errori
 * di parsing sono gestiti centralmente (log + {@link Optional} vuoto).
 */
public final class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static final JsonMapper JSON_MAPPER = JsonMapper.builder().findAndAddModules().build();

    private JsonUtil() {
    }

    public static Optional<String> serializeToJson(Object value) {
        try {
            return Optional.of(JSON_MAPPER.writeValueAsString(value));
        } catch (JsonProcessingException ex) {
            log.warn("Serializzazione JSON fallita: {}", ex.getMessage());
            return Optional.empty();
        }
    }

    public static <T> Optional<T> deserializeFromJson(String jsonText, Class<T> targetType) {
        try {
            return Optional.ofNullable(JSON_MAPPER.readValue(jsonText, targetType));
        } catch (JsonProcessingException | IllegalArgumentException ex) {
            log.warn("Deserializzazione JSON fallita: {}", ex.getMessage());
            return Optional.empty();
        }
    }
}
