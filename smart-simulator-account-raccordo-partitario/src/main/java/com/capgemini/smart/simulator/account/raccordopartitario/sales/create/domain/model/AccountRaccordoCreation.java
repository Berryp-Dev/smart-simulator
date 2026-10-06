package com.capgemini.smart.simulator.account.raccordopartitario.sales.create.domain.model;

/**
 * Dati in ingresso per la creazione di un rapporto di raccordo partitario.
 * I codici possono essere assenti: nessuna validazione a livello di dominio.
 *
 * @param bankCode    codice banca (ABI), se presente nella richiesta
 * @param serviceCode codice servizio, se presente nella richiesta
 */
public record AccountRaccordoCreation(String bankCode, String serviceCode) {
}
