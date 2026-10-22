package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributes;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributesSearchCriteria;

/**
 * Driving port dell'API getAdditionalAttributes.
 */
public interface RetrieveServiceAccountAdditionalAttributesService {

    ServiceAccountAdditionalAttributes getServiceAccountAdditionalAttributes(ServiceAccountAdditionalAttributesSearchCriteria serviceAccountAdditionalAttributesSearchCriteria);
}
