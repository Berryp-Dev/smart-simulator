package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.application.port.in.UpdateFinalNdgService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setfinalndg.domain.model.FinalNdgAssignment;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class UpdateFinalNdgServiceImpl implements UpdateFinalNdgService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: setFinalNdg";

    @Override
    public void updateFinalNdg(FinalNdgAssignment finalNdgAssignment) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
