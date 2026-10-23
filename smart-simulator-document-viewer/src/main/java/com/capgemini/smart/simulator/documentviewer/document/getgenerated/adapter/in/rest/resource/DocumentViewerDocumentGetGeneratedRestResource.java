package com.capgemini.smart.simulator.documentviewer.document.getgenerated.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.rest.ApiError;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.adapter.in.rest.mapper.DocumentViewerDocumentGetGeneratedMapper;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.in.RetrieveGetGeneratedDocumentService;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GeneratedDocument;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GetGeneratedDocumentCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dell'API document del Document Viewer. Servizio
 * getGenerated: decripta bnlToken e restituisce il PDF inline, per la
 * visualizzazione nel browser.
 */
@RestController
@Tag(name = "Document Viewer / Document")
@RequestMapping("/document-viewer/document")
public class DocumentViewerDocumentGetGeneratedRestResource {

    @Autowired
    private RetrieveGetGeneratedDocumentService retrieveGetGeneratedDocumentService;

    @Operation(operationId = "getGenerated", summary = "Visualizza il PDF generato identificato da bnlToken")
    @ApiResponse(responseCode = "200", description = "Contenuto del PDF",
            content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE,
                    schema = @Schema(type = "string", format = "binary")))
    @ApiResponse(responseCode = "400", description = "Parametro non valido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "404", description = "Documento non trovato",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
    @GetMapping("/getGenerated")
    public ResponseEntity<byte[]> getGenerated(@RequestParam(name = "appName", required = false) String appName,
                                                @RequestParam(name = "trackingId", required = false) String trackingId,
                                                @RequestParam(name = "callUnseal", required = false) String callUnseal,
                                                @RequestParam(name = "bnlToken", required = false) String bnlToken) {
        GetGeneratedDocumentCriteria criteria =
                DocumentViewerDocumentGetGeneratedMapper.mapToDomain(appName, trackingId, callUnseal, bnlToken);
        GeneratedDocument document = retrieveGetGeneratedDocumentService.getGeneratedDocument(criteria);
        ContentDisposition contentDisposition = ContentDisposition.inline()
                .filename(document.documentId() + ".pdf")
                .build();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .body(document.content());
    }
}
