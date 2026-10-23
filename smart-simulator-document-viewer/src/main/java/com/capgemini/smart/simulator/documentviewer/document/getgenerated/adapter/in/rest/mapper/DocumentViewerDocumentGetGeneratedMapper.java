package com.capgemini.smart.simulator.documentviewer.document.getgenerated.adapter.in.rest.mapper;

import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GetGeneratedDocumentCriteria;

/**
 * Mapping tra i query param REST e i criteri di ricerca di dominio. Il
 * mapper non valida: un parametro assente resta null nel dominio.
 */
public final class DocumentViewerDocumentGetGeneratedMapper {

    private DocumentViewerDocumentGetGeneratedMapper() {
    }

    public static GetGeneratedDocumentCriteria mapToDomain(String appName, String trackingId, String callUnseal, String bnlToken) {
        return new GetGeneratedDocumentCriteria(appName, trackingId, callUnseal, bnlToken);
    }
}
