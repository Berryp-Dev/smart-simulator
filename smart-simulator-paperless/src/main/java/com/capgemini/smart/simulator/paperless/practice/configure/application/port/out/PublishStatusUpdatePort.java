package com.capgemini.smart.simulator.paperless.practice.configure.application.port.out;

import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeStatusUpdate;

/**
 * Driven port per la pubblicazione dell'aggiornamento di stato su IBM MQ.
 */
public interface PublishStatusUpdatePort {

    void publishStatusUpdate(PaperlessPracticeStatusUpdate statusUpdate);
}
