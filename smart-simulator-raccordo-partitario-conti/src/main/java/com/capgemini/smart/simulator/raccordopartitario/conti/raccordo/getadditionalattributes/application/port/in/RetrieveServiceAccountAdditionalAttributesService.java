package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.application.port.in;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributes;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getadditionalattributes.domain.model.ServiceAccountAdditionalAttributesSearchCriteria;

/**
 * Driving port dell'API getAdditionalAttributes.
 */
public interface RetrieveServiceAccountAdditionalAttributesService {

    ServiceAccountAdditionalAttributes getServiceAccountAdditionalAttributes(ServiceAccountAdditionalAttributesSearchCriteria serviceAccountAdditionalAttributesSearchCriteria);
}
