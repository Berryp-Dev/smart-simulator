package com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception;

/**
 * Eccezione di dominio: il servizio richiamato non e' ancora implementato.
 * Puro Java, nessuna dipendenza da Spring o dal core. L'adapter REST la traduce in HTTP 501.
 */
public class ServiceNotImplementedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceNotImplementedException(String message) {
        super(message);
    }
}
