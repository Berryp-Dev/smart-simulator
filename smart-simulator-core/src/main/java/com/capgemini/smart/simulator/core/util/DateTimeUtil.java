package com.capgemini.smart.simulator.core.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility comuni di formattazione/parsing per date e orari (java.time, formato ISO-8601).
 * Il parsing di una stringa non valida solleva {@link java.time.format.DateTimeParseException}.
 * <p>
 * Scelta di design intenzionale: a differenza di {@link NumberUtil} e {@link JsonUtil}
 * (dove un valore non convertibile e' un caso d'uso normale, gestito con {@link java.util.Optional}),
 * qui una data/ora non convertibile e' considerata un errore bloccante e viene segnalata
 * tramite eccezione, non con un Optional vuoto. La differenza di comportamento non e' una
 * inconsistenza.
 */
public final class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static String formatInstant(Instant instant) {
        return DateTimeFormatter.ISO_INSTANT.format(instant);
    }

    public static Instant parseInstant(String instantText) {
        return Instant.parse(instantText);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
    }

    public static LocalDateTime parseLocalDateTime(String localDateTimeText) {
        return LocalDateTime.parse(localDateTimeText, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
