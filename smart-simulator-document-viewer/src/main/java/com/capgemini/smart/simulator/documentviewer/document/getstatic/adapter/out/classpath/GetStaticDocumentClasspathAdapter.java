package com.capgemini.smart.simulator.documentviewer.document.adapter.out.classpath;

import com.capgemini.smart.simulator.documentviewer.config.DocumentViewerProperties;
import com.capgemini.smart.simulator.documentviewer.document.application.port.out.LoadGetStaticDocumentPort;
import com.capgemini.smart.simulator.documentviewer.document.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.document.domain.model.GetStaticDocumentSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Adapter classpath della port {@link LoadGetStaticDocumentPort}: legge il PDF
 * {@code <productCode>.pdf} dalla cartella del classpath del modulo indicata
 * da {@code products-folder} (i PDF finiscono nel jar). Il file si cerca
 * elencando i {@code .pdf} della cartella e confrontando il nome in modo
 * esatto e case-sensitive (su Windows {@code target/classes} ignora le
 * maiuscole, il jar no: il confronto esplicito vale in entrambi i casi).
 * Nessun percorso e' costruito a partire da {@code productCode}.
 */
@Component
public class GetStaticDocumentClasspathAdapter implements LoadGetStaticDocumentPort {

    private static final String CLASSPATH_PREFIX = "classpath:";
    private static final String PDF_EXTENSION = ".pdf";

    @Autowired
    private DocumentViewerProperties properties;
    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @Override
    public Optional<Document> getStaticDocument(GetStaticDocumentSearchCriteria criteria) {
        String fileName = criteria.productCode() + PDF_EXTENSION;
        return findExactResource(fileName)
                .map(resource -> new Document(criteria.productCode(), readBytes(resource)));
    }

    private Optional<Resource> findExactResource(String fileName) {
        String pattern = CLASSPATH_PREFIX + normalizeFolder(properties.getProductsFolder()) + "/*" + PDF_EXTENSION;
        try {
            return Arrays.stream(resourcePatternResolver.getResources(pattern))
                    .filter(resource -> fileName.equals(resource.getFilename()))
                    .findFirst();
        } catch (FileNotFoundException e) {
            // Cartella assente nel classpath: nessun documento
            return Optional.empty();
        } catch (IOException e) {
            throw new UncheckedIOException("Lettura della cartella dei PDF non riuscita: " + pattern, e);
        }
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
            throw new UncheckedIOException("Lettura del documento non riuscita: " + resource.getFilename(), e);
        }
    }
}
