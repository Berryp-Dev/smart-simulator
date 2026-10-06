package com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model;

import java.time.Instant;

/**
 * Esito della creazione di un rapporto di raccordo partitario.
 *
 * @param bankCode          codice banca riportato dall'input
 * @param serviceCode       codice servizio riportato dall'input
 * @param serviceAccountKey chiave generata del rapporto (UUID)
 * @param status            stato del rapporto (es. "CREATED")
 * @param statusDescription descrizione dello stato (es. "Creato")
 * @param statusTimestamp   istante di assegnazione dello stato
 */
public record AccountRaccordoCreationResult(
        String bankCode,
        String serviceCode,
        String serviceAccountKey,
        String status,
        String statusDescription,
        Instant statusTimestamp) {
}
