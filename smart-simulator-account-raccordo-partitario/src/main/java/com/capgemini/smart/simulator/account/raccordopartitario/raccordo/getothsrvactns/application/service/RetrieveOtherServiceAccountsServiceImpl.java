package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.application.service;

import com.capgemini.smart.simulator.account.raccordopartitario.common.domain.exception.ServiceNotImplementedException;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.application.port.in.RetrieveOtherServiceAccountsService;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model.OtherServiceAccounts;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model.OtherServiceAccountsSearchCriteria;
import org.springframework.stereotype.Service;

/**
 * Servizio non implementato: ogni chiamata lancia {@link ServiceNotImplementedException}.
 * L'implementazione verra' realizzata in un intervento successivo.
 */
@Service
public class RetrieveOtherServiceAccountsServiceImpl implements RetrieveOtherServiceAccountsService {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Servizio non implementato: getOthSrvActns";

    @Override
    public OtherServiceAccounts getOtherServiceAccounts(OtherServiceAccountsSearchCriteria otherServiceAccountsSearchCriteria) {
        throw new ServiceNotImplementedException(NOT_IMPLEMENTED_MESSAGE);
    }
}
