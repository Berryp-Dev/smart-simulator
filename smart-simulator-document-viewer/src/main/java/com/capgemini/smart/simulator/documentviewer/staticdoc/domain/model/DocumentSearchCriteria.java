package com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model;

/**
 * Criteri di ricerca del documento statico: codice prodotto. Un valore
 * assente resta null (la presenza e' controllata dal validator).
 */
public record DocumentSearchCriteria(String productCode) {
}
