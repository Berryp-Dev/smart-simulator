package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.domain.model.ServiceAccountActivation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.activate.domain.model.ServiceAccountActivationResult;

/**
 * Driving port dell'API activate.
 */
public interface ActivateServiceAccountService {

    ServiceAccountActivationResult activateServiceAccount(ServiceAccountActivation serviceAccountActivation);
}
