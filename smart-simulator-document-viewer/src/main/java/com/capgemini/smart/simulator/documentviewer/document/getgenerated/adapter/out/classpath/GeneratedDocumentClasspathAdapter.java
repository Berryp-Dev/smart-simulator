package com.capgemini.smart.simulator.documentviewer.document.getgenerated.adapter.out.classpath;

import com.capgemini.smart.simulator.documentviewer.config.DocumentViewerProperties;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.out.LoadGeneratedDocumentPort;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GeneratedDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Optional;

/**
 * Adapter classpath della port {@link LoadGeneratedDocumentPort}: non esistono
 * ancora documenti generati reali, quindi ogni id restituisce sempre lo stesso
 * PDF generico {@code <generated-products-folder>/<default-generated-name>}
 * (il parametro documentId non influisce sulla scelta del file).
 */
@Component
public class GeneratedDocumentClasspathAdapter implements LoadGeneratedDocumentPort {

    @Autowired
    private DocumentViewerProperties properties;

    @Override
    public Optional<GeneratedDocument> getGeneratedDocument(String documentId) {
        Resource resource = new ClassPathResource(
                normalizeFolder(properties.getGeneratedProductsFolder()) + "/" + properties.getDefaultGeneratedName());
        if (!resource.exists()) {
            return Optional.empty();
        }
        return Optional.of(new GeneratedDocument(documentId, readBytes(resource)));
    }

    /** Toglie gli eventuali slash iniziali e finali del valore configurato. */
    private String normalizeFolder(String folder) {
        String normalized = folder.trim();
        while (normalized.startsWith("/")) {
            normalized = normalized.substring(1);
        }
        while (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    private byte[] readBytes(Resource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new UncheckedIOException("Lettura del documento generato non riuscita: " + resource.getFilename(), e);
        }
    }
}
