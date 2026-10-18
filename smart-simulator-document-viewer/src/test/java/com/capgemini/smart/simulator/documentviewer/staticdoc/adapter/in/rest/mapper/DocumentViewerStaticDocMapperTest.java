package com.capgemini.smart.simulator.documentviewer.staticdoc.adapter.in.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;
import org.junit.jupiter.api.Test;

class DocumentViewerStaticDocMapperTest {

    @Test
    void mapToDomain_valuePresent_keepsValue() {
        DocumentSearchCriteria criteria = DocumentViewerStaticDocMapper.mapToDomain("12234");

        assertEquals("12234", criteria.productCode());
    }

    @Test
    void mapToDomain_valueNull_keepsNull() {
        DocumentSearchCriteria criteria = DocumentViewerStaticDocMapper.mapToDomain(null);

        assertNull(criteria.productCode());
    }
}
