package com.capgemini.smart.simulator.documentviewer.document.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.documentviewer.document.domain.model.GetStaticDocumentSearchCriteria;
import org.junit.jupiter.api.Test;

class DocumentViewerDocumentMapperTest {

    @Test
    void mapToDomain_valuePresent_keepsValue() {
        GetStaticDocumentSearchCriteria criteria = DocumentViewerDocumentMapper.mapToDomain("12234");

        assertEquals("12234", criteria.productCode());
    }

    @Test
    void mapToDomain_valueNull_keepsNull() {
        GetStaticDocumentSearchCriteria criteria = DocumentViewerDocumentMapper.mapToDomain(null);

        assertNull(criteria.productCode());
    }
}
