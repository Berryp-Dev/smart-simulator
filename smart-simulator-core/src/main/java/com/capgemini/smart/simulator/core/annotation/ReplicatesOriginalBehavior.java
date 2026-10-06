package com.capgemini.smart.simulator.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marca in modo esplicito e cercabile (grep/IDE) i punti del codice che
 * derogano dallo standard del progetto per aderire fedelmente al contratto
 * di un sistema esterno reale simulato (request/response DTO, naming campi,
 * status code, formato errori, logica di validazione).
 * <p>
 * E' una eccezione DICHIARATA: va usata solo quando una specifica indica
 * "Comportamento: REPLICA ORIGINALE". Si applica al controller o al singolo
 * metodo interessato.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ReplicatesOriginalBehavior {

    /** Nome del sistema originale simulato (obbligatorio). */
    String system();

    /** Riferimento a WSDL/documentazione/specifica del sistema originale (opzionale). */
    String reference() default "";
}
