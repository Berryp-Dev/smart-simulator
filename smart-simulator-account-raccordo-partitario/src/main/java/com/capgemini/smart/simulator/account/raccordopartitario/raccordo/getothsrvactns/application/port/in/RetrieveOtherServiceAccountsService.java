package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model.OtherServiceAccounts;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getothsrvactns.domain.model.OtherServiceAccountsSearchCriteria;

/**
 * Driving port dell'API getOthSrvActns.
 */
public interface RetrieveOtherServiceAccountsService {

    OtherServiceAccounts getOtherServiceAccounts(OtherServiceAccountsSearchCriteria otherServiceAccountsSearchCriteria);
}
