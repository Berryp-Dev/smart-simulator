package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.application.port.in;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfo;
import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.getmaininfo.domain.model.ServiceAccountMainInfoSearchCriteria;

/**
 * Driving port dell'API getMainInfo.
 */
public interface RetrieveServiceAccountMainInfoService {

    ServiceAccountMainInfo getServiceAccountMainInfo(ServiceAccountMainInfoSearchCriteria serviceAccountMainInfoSearchCriteria);
}
