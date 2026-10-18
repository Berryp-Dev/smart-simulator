package com.capgemini.smart.simulator.documentviewer.staticdoc.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.rest.ApiError;
import com.capgemini.smart.simulator.documentviewer.staticdoc.adapter.in.rest.mapper.DocumentViewerStaticDocMapper;
import com.capgemini.smart.simulator.documentviewer.staticdoc.application.port.in.RetrieveDocumentService;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;
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
 * Entry point REST dello scenario Static Doc (retrieve): restituisce il PDF
 * inline, per la visualizzazione nel browser.
 */
@RestController
@Tag(name = "Document")
@RequestMapping("/document-viewer")
public class DocumentViewerStaticDocRestResource {

    @Autowired
    private RetrieveDocumentService retrieveDocumentService;

    @Operation(summary = "Visualizza il PDF del prodotto indicato")
    @ApiResponse(responseCode = "200", description = "Contenuto del PDF",
            content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE,
                    schema = @Schema(type = "string", format = "binary")))
    @ApiResponse(responseCode = "400", description = "Parametro non valido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "404", description = "Documento non trovato",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
    @GetMapping("/staticdoc")
    public ResponseEntity<byte[]> getDocument(@RequestParam(name = "productCode", required = false) String productCode) {
        DocumentSearchCriteria criteria = DocumentViewerStaticDocMapper.mapToDomain(productCode);
        Document document = retrieveDocumentService.getDocument(criteria);
        ContentDisposition contentDisposition = ContentDisposition.inline()
                .filename(document.productCode() + ".pdf")
                .build();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .body(document.content());
    }
}
