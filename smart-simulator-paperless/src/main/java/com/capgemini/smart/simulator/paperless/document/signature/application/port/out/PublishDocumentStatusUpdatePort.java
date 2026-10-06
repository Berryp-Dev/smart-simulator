package com.capgemini.smart.simulator.paperless.document.signature.application.port.out;

import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentStatusUpdate;

/**
 * Driven port per la pubblicazione, per singolo documento, dell'aggiornamento
 * di stato su IBM MQ.
 */
public interface PublishDocumentStatusUpdatePort {

    void publishDocumentStatusUpdate(PaperlessDocumentStatusUpdate documentStatusUpdate);
}
