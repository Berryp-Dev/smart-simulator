package com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model;

/**
 * Criteri del servizio getGenerated: tutti i campi sono obbligatori (la
 * presenza e il formato sono controllati dal validator). trackingId resta
 * stringa a questo livello (il formato UUID e' un controllo del validator,
 * non un vincolo di tipo).
 */
public record GetGeneratedDocumentCriteria(String appName, String trackingId, String callUnseal, String bnlToken) {
}
