package com.capgemini.smart.simulator.core.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility per la generazione di stringhe di cifre numeriche random (es.
 * identificativi di pratica/processo nei messaggi pubblicati dai simulatori).
 * Ogni cifra e' generata indipendentemente, quindi la stringa puo' iniziare
 * con uno o piu' zeri.
 */
public final class RandomUtil {

    private RandomUtil() {
    }

    public static String generateNumericString(int length) {
        StringBuilder numericString = new StringBuilder(length);
        for (int digitIndex = 0; digitIndex < length; digitIndex++) {
            numericString.append(ThreadLocalRandom.current().nextInt(10));
        }
        return numericString.toString();
    }
}
