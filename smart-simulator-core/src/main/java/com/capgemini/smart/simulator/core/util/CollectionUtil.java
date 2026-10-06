package com.capgemini.smart.simulator.core.util;

import java.util.Collection;

/**
 * Utility comuni per le collezioni.
 */
public final class CollectionUtil {

    private CollectionUtil() {
    }

    /** True se la collezione e' null o vuota. */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /** Alias semantico di {@link #isNotEmpty(Collection)} per la leggibilita' del chiamante. */
    public static boolean hasElements(Collection<?> collection) {
        return isNotEmpty(collection);
    }
}
