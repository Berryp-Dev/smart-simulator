package com.capgemini.smart.simulator.core.util;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Utility comuni per i valori numerici. Il parsing e' sicuro: non lancia
 * eccezioni ma restituisce un {@link Optional} vuoto se il testo non e' valido.
 */
public final class NumberUtil {

    private NumberUtil() {
    }

    /** True se il valore e' null oppure uguale a zero. */
    public static boolean isNullOrZero(Number number) {
        return number == null || new BigDecimal(number.toString()).signum() == 0;
    }

    public static Optional<Integer> parseInteger(String numberText) {
        try {
            return Optional.of(Integer.parseInt(numberText.trim()));
        } catch (NumberFormatException | NullPointerException ex) {
            return Optional.empty();
        }
    }

    public static Optional<Long> parseLong(String numberText) {
        try {
            return Optional.of(Long.parseLong(numberText.trim()));
        } catch (NumberFormatException | NullPointerException ex) {
            return Optional.empty();
        }
    }

    public static Optional<BigDecimal> parseBigDecimal(String numberText) {
        try {
            return Optional.of(new BigDecimal(numberText.trim()));
        } catch (NumberFormatException | NullPointerException ex) {
            return Optional.empty();
        }
    }
}
