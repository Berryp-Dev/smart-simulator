package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.application.port.in;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model.ServiceAccountActivation;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.activate.domain.model.ServiceAccountActivationResult;

/**
 * Driving port dell'API activate.
 */
public interface ActivateServiceAccountService {

    ServiceAccountActivationResult activateServiceAccount(ServiceAccountActivation serviceAccountActivation);
}
