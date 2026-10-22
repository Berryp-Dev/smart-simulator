package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.application.port.in.RetrieveServiceAccountMainInfoService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfoSearchCriteria;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class RetrieveServiceAccountMainInfoServiceImpl implements RetrieveServiceAccountMainInfoService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: getMainInfo";

    @Override
    public ServiceAccountMainInfo getServiceAccountMainInfo(ServiceAccountMainInfoSearchCriteria serviceAccountMainInfoSearchCriteria) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
