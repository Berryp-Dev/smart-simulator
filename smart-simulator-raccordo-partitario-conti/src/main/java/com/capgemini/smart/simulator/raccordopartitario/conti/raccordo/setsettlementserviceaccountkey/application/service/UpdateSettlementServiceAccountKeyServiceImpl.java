package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.application.service;

import com.capgemini.smart.simulator.raccordopartitario.conti.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.application.port.in.UpdateSettlementServiceAccountKeyService;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.domain.model.SettlementServiceAccountKeyAssignment;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class UpdateSettlementServiceAccountKeyServiceImpl implements UpdateSettlementServiceAccountKeyService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: setSettlementServiceAccountKey";

    @Override
    public void updateSettlementServiceAccountKey(SettlementServiceAccountKeyAssignment settlementServiceAccountKeyAssignment) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
