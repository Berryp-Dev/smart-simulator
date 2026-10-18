package com.capgemini.smart.simulator.documentviewer.document.adapter.out.classpath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.documentviewer.config.DocumentViewerProperties;
import com.capgemini.smart.simulator.documentviewer.document.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.document.domain.model.GetStaticDocumentSearchCriteria;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.util.ReflectionTestUtils;

class GetStaticDocumentClasspathAdapterTest {

    private static final String TEST_FOLDER = "test-pdf";

    private GetStaticDocumentClasspathAdapter adapter;
    private DocumentViewerProperties properties;

    @BeforeEach
    void setUp() {
        properties = new DocumentViewerProperties();
        properties.setProductsFolder(TEST_FOLDER);
        adapter = new GetStaticDocumentClasspathAdapter();
        ReflectionTestUtils.setField(adapter, "properties", properties);
        ReflectionTestUtils.setField(adapter, "resourcePatternResolver", new PathMatchingResourcePatternResolver());
    }

    @Test
    void getStaticDocument_filePresent_returnsContent() {
        Optional<Document> result = adapter.getStaticDocument(new GetStaticDocumentSearchCriteria("TESTDOC1"));

        assertTrue(result.isPresent());
        assertEquals("TESTDOC1", result.get().productCode());
        assertTrue(new String(result.get().content(), StandardCharsets.ISO_8859_1).startsWith("%PDF"));
    }

    @Test
    void getStaticDocument_secondFilePresent_returnsContent() {
        Optional<Document> result = adapter.getStaticDocument(new GetStaticDocumentSearchCriteria("testdoc2"));

        assertTrue(result.isPresent());
        assertTrue(new String(result.get().content(), StandardCharsets.ISO_8859_1).startsWith("%PDF"));
    }

    @Test
    void getStaticDocument_fileAbsent_returnsEmpty() {
        Optional<Document> result = adapter.getStaticDocument(new GetStaticDocumentSearchCriteria("NOPE"));

        assertTrue(result.isEmpty());
    }

    @Test
    void getStaticDocument_differentCase_returnsEmpty() {
        // Il confronto e' sul nome delle risorse elencate: vale anche su target/classes (Windows)
        Optional<Document> result = adapter.getStaticDocument(new GetStaticDocumentSearchCriteria("testdoc1"));

        assertTrue(result.isEmpty());
    }

    @Test
    void getStaticDocument_folderAbsent_returnsEmpty() {
        properties.setProductsFolder("cartella-inesistente");

        Optional<Document> result = adapter.getStaticDocument(new GetStaticDocumentSearchCriteria("TESTDOC1"));

        assertTrue(result.isEmpty());
    }

    @Test
    void getStaticDocument_folderWithSurroundingSlashes_isNormalized() {
        properties.setProductsFolder("/" + TEST_FOLDER + "/");

        Optional<Document> result = adapter.getStaticDocument(new GetStaticDocumentSearchCriteria("TESTDOC1"));

        assertTrue(result.isPresent());
    }
}
