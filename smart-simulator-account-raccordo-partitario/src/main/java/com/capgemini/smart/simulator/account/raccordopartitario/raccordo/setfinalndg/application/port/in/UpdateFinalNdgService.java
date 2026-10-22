package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.setfinalndg.domain.model.FinalNdgAssignment;

/**
 * Driving port dell'API setFinalNdg.
 */
public interface UpdateFinalNdgService {

    void updateFinalNdg(FinalNdgAssignment finalNdgAssignment);
}
