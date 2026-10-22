package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.ServiceAccountCreationCheck;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.ServiceAccountCreationCheckResult;

/**
 * Driving port dell'API canBeCreated.
 */
public interface CheckServiceAccountCreationService {

    ServiceAccountCreationCheckResult checkServiceAccountCreation(ServiceAccountCreationCheck serviceAccountCreationCheck);
}
