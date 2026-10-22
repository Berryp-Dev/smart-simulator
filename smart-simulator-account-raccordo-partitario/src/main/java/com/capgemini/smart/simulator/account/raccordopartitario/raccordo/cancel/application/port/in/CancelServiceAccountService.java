package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.cancel.domain.model.ServiceAccountCancellation;

/**
 * Driving port dell'API cancel.
 */
public interface CancelServiceAccountService {

    void cancelServiceAccount(ServiceAccountCancellation serviceAccountCancellation);
}
