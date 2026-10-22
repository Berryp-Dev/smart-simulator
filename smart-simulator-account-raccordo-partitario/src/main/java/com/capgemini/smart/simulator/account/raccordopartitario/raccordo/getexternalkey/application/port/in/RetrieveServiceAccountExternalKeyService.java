package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.domain.model.ServiceAccountExternalKey;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getexternalkey.domain.model.ServiceAccountExternalKeySearchCriteria;

/**
 * Driving port dell'API getExternalKey.
 */
public interface RetrieveServiceAccountExternalKeyService {

    ServiceAccountExternalKey getServiceAccountExternalKey(ServiceAccountExternalKeySearchCriteria serviceAccountExternalKeySearchCriteria);
}
