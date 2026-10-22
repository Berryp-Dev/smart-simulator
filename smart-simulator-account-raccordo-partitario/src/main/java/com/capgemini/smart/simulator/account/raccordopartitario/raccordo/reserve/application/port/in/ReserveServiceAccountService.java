package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.application.port.in;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.domain.model.ServiceAccountReservation;
import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.reserve.domain.model.ServiceAccountReservationResult;

/**
 * Driving port dell'API reserve.
 */
public interface ReserveServiceAccountService {

    ServiceAccountReservationResult reserveServiceAccount(ServiceAccountReservation serviceAccountReservation);
}
