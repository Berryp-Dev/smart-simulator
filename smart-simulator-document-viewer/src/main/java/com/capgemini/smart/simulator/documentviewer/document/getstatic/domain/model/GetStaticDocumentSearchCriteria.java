package com.capgemini.smart.simulator.documentviewer.document.getstatic.domain.model;

/**
 * Criteri di ricerca del servizio getStatic: codice prodotto. Un valore
 * assente resta null (la presenza e' controllata dal validator).
 */
public record GetStaticDocumentSearchCriteria(String productCode) {
}
