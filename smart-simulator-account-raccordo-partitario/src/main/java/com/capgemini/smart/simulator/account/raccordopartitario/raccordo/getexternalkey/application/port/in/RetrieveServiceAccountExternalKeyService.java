package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.application.port.in;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKey;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getexternalkey.domain.model.ServiceAccountExternalKeySearchCriteria;

/**
 * Driving port dell'API getExternalKey.
 */
public interface RetrieveServiceAccountExternalKeyService {

    ServiceAccountExternalKey getServiceAccountExternalKey(ServiceAccountExternalKeySearchCriteria serviceAccountExternalKeySearchCriteria);
}
