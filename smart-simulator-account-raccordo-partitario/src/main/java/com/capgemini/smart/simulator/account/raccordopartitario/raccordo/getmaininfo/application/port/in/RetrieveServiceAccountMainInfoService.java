package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.domain.model.ServiceAccountMainInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.getmaininfo.domain.model.ServiceAccountMainInfoSearchCriteria;

/**
 * Driving port dell'API getMainInfo.
 */
public interface RetrieveServiceAccountMainInfoService {

    ServiceAccountMainInfo getServiceAccountMainInfo(ServiceAccountMainInfoSearchCriteria serviceAccountMainInfoSearchCriteria);
}
