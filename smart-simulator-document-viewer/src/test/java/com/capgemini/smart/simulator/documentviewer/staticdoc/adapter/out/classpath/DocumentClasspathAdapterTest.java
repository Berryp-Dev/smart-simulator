package com.capgemini.smart.simulator.documentviewer.staticdoc.adapter.out.classpath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.documentviewer.config.DocumentViewerProperties;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.util.ReflectionTestUtils;

class DocumentClasspathAdapterTest {

    private static final String TEST_FOLDER = "test-pdf";

    private DocumentClasspathAdapter adapter;
    private DocumentViewerProperties properties;

    @BeforeEach
    void setUp() {
        properties = new DocumentViewerProperties();
        properties.setProductsFolder(TEST_FOLDER);
        adapter = new DocumentClasspathAdapter();
        ReflectionTestUtils.setField(adapter, "properties", properties);
        ReflectionTestUtils.setField(adapter, "resourcePatternResolver", new PathMatchingResourcePatternResolver());
    }

    @Test
    void getDocument_filePresent_returnsContent() {
        Optional<Document> result = adapter.getDocument(new DocumentSearchCriteria("TESTDOC1"));

        assertTrue(result.isPresent());
        assertEquals("TESTDOC1", result.get().productCode());
        assertTrue(new String(result.get().content(), StandardCharsets.ISO_8859_1).startsWith("%PDF"));
    }

    @Test
    void getDocument_secondFilePresent_returnsContent() {
        Optional<Document> result = adapter.getDocument(new DocumentSearchCriteria("testdoc2"));

        assertTrue(result.isPresent());
        assertTrue(new String(result.get().content(), StandardCharsets.ISO_8859_1).startsWith("%PDF"));
    }

    @Test
    void getDocument_fileAbsent_returnsEmpty() {
        Optional<Document> result = adapter.getDocument(new DocumentSearchCriteria("NOPE"));

        assertTrue(result.isEmpty());
    }

    @Test
    void getDocument_differentCase_returnsEmpty() {
        // Il confronto e' sul nome delle risorse elencate: vale anche su target/classes (Windows)
        Optional<Document> result = adapter.getDocument(new DocumentSearchCriteria("testdoc1"));

        assertTrue(result.isEmpty());
    }

    @Test
    void getDocument_folderAbsent_returnsEmpty() {
        properties.setProductsFolder("cartella-inesistente");

        Optional<Document> result = adapter.getDocument(new DocumentSearchCriteria("TESTDOC1"));

        assertTrue(result.isEmpty());
    }

    @Test
    void getDocument_folderWithSurroundingSlashes_isNormalized() {
        properties.setProductsFolder("/" + TEST_FOLDER + "/");

        Optional<Document> result = adapter.getDocument(new DocumentSearchCriteria("TESTDOC1"));

        assertTrue(result.isPresent());
    }
}
