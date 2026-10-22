package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.application.port.in.ReserveServiceAccountService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.domain.model.ServiceAccountReservation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.reserve.domain.model.ServiceAccountReservationResult;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class ReserveServiceAccountServiceImpl implements ReserveServiceAccountService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: reserve";

    @Override
    public ServiceAccountReservationResult reserveServiceAccount(ServiceAccountReservation serviceAccountReservation) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
