package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.application.port.in;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.domain.model.FinalNdgAssignment;

/**
 * Driving port dell'API setFinalNdg.
 */
public interface UpdateFinalNdgService {

    void updateFinalNdg(FinalNdgAssignment finalNdgAssignment);
}
