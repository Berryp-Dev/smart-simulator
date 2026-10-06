package com.capgemini.smart.simulator.core.config;

import java.util.Map;

/**
 * Contratto per la risoluzione dei valori di default quando una request non
 * specifica esplicitamente un dato campo. L'implementazione base
 * ({@link StaticDefaultsResolver}) legge da configurazione statica
 * (application.yml); un modulo sottosistema con logica di default piu'
 * complessa (es. dipendente da canale, tipo operazione, orario) puo'
 * fornire una propria implementazione e sostituire il bean di default.
 */
public interface DefaultsResolver {

    /**
     * Risolve il valore di default per una data proprieta', nell'ambito di
     * un dato sottosistema.
     *
     * @param subsystem  identificativo del sottosistema simulato (es. "paperless")
     * @param property   nome logico della proprieta' da risolvere (es. "priority")
     * @param conditions condizioni aggiuntive che possono influenzare il default
     *                   (es. canale, tipo operazione, orario) - puo' essere vuota
     * @return il valore di default risolto, oppure {@code null} se non esiste
     *         un default applicabile
     */
    Object resolve(String subsystem, String property, Map<String, Object> conditions);
}
