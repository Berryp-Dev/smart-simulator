package com.capgemini.smart.simulator.account.raccordopartitario.common.adapter.in.rest.exception;

/**
 * Sollevata da ogni operazione REST del modulo non ancora implementata.
 * Porta con se' il corpo della richiesta (se presente) per consentire
 * all'advice di riecheggiare l'header nella risposta HTTP 501.
 */
public class NotImplementedOperationException extends RuntimeException {

    private final transient Object request;

    public NotImplementedOperationException(Object request) {
        super("Service not implemented");
        this.request = request;
    }

    public Object getRequest() {
        return request;
    }
}
