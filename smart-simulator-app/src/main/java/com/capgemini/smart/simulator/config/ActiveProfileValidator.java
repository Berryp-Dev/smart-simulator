package com.capgemini.smart.simulator.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Controlla all'avvio che sia attivo esattamente un profilo Spring tra quelli
 * ammessi. Se il profilo manca, non e' ammesso o ne sono attivi piu' di uno,
 * l'applicazione non parte. Interviene prima della creazione dei bean.
 */
public class ActiveProfileValidator implements EnvironmentPostProcessor {

    /** Unico punto in cui sono elencati i profili ammessi. */
    static final List<String> ALLOWED_PROFILES = List.of("dev", "sit");

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        validate(Arrays.asList(environment.getActiveProfiles()));
    }

    /** Verifica i profili attivi e lancia {@link IllegalStateException} se non validi. */
    static void validate(List<String> activeProfiles) {
        if (activeProfiles.size() != 1 || !isAllowed(activeProfiles.get(0))) {
            throw new IllegalStateException(buildErrorMessage(activeProfiles));
        }
    }

    private static boolean isAllowed(String profile) {
        return ALLOWED_PROFILES.contains(profile);
    }

    private static String buildErrorMessage(List<String> activeProfiles) {
        String found = activeProfiles.isEmpty() ? "nessun profilo attivo" : "profili attivi: " + activeProfiles;
        return "Profilo Spring non valido (" + found + "): deve essere attivo esattamente un profilo tra "
                + ALLOWED_PROFILES + ". Impostare SPRING_PROFILES_ACTIVE (o --spring.profiles.active).";
    }
}
