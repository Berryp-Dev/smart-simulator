package com.capgemini.smart.simulator.documentviewer.document.getstatic.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.documentviewer.document.getstatic.domain.model.GetStaticDocumentSearchCriteria;
import org.junit.jupiter.api.Test;

class DocumentViewerDocumentGetStaticMapperTest {

    @Test
    void mapToDomain_valuePresent_keepsValue() {
        GetStaticDocumentSearchCriteria criteria = DocumentViewerDocumentGetStaticMapper.mapToDomain("12234");

        assertEquals("12234", criteria.productCode());
    }

    @Test
    void mapToDomain_valueNull_keepsNull() {
        GetStaticDocumentSearchCriteria criteria = DocumentViewerDocumentGetStaticMapper.mapToDomain(null);

        assertNull(criteria.productCode());
    }
}
