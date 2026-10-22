package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.application.port.in;

import com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.setsettlementserviceaccountkey.domain.model.SettlementServiceAccountKeyAssignment;

/**
 * Driving port dell'API setSettlementServiceAccountKey.
 */
public interface UpdateSettlementServiceAccountKeyService {

    void updateSettlementServiceAccountKey(SettlementServiceAccountKeyAssignment settlementServiceAccountKeyAssignment);
}
