package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.application.port.in;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model.ServiceAccountCreation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.create.domain.model.ServiceAccountCreationResult;

/**
 * Driving port dell'API create.
 */
public interface CreateServiceAccountService {

    ServiceAccountCreationResult createServiceAccount(ServiceAccountCreation serviceAccountCreation);
}
