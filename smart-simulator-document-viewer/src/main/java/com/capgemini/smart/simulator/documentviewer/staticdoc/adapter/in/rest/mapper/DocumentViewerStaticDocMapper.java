package com.capgemini.smart.simulator.documentviewer.staticdoc.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;

/**
 * Mapping tra il query param REST e i criteri di ricerca di dominio. Il
 * mapper non valida: un parametro assente resta null nel dominio.
 */
public final class DocumentViewerStaticDocMapper {

    private DocumentViewerStaticDocMapper() {
    }

    public static DocumentSearchCriteria mapToDomain(String productCode) {
        return new DocumentSearchCriteria(productCode);
    }
}
