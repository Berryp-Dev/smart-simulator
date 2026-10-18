package com.capgemini.smart.simulator.documentviewer.document.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.documentviewer.document.domain.model.GetStaticDocumentSearchCriteria;

/**
 * Mapping tra il query param REST e i criteri di ricerca di dominio. Il
 * mapper non valida: un parametro assente resta null nel dominio.
 */
public final class DocumentViewerDocumentMapper {

    private DocumentViewerDocumentMapper() {
    }

    public static GetStaticDocumentSearchCriteria mapToDomain(String productCode) {
        return new GetStaticDocumentSearchCriteria(productCode);
    }
}
