package com.capgemini.smart.simulator.core.util;

/**
 * Utility comuni per le stringhe.
 */
public final class StringUtil {

    private StringUtil() {
    }

    /** True se la stringa e' null, vuota o composta solo da spazi. */
    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }
}
