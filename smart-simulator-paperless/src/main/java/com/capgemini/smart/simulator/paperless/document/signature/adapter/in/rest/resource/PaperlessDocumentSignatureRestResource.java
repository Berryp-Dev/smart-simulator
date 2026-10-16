package com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.resource;

import com.capgemini.smart.simulator.core.rest.ApiError;
import com.capgemini.smart.simulator.core.rest.request.RestRequest;
import com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.dto.PaperlessDocumentSignatureDtoReq;
import com.capgemini.smart.simulator.paperless.document.signature.adapter.in.rest.mapper.PaperlessDocumentSignatureMapper;
import com.capgemini.smart.simulator.paperless.document.signature.application.port.in.NotifyPaperlessDocumentSignatureUseCase;
import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentSignature;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point REST dello scenario Document Signature.
 */
@RestController
@Tag(name = "Document")
@RequestMapping("/paperless/document")
public class PaperlessDocumentSignatureRestResource {

    private final NotifyPaperlessDocumentSignatureUseCase notifyPaperlessDocumentSignatureUseCase;

    public PaperlessDocumentSignatureRestResource(NotifyPaperlessDocumentSignatureUseCase notifyPaperlessDocumentSignatureUseCase) {
        this.notifyPaperlessDocumentSignatureUseCase = notifyPaperlessDocumentSignatureUseCase;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notifica di firma documento pubblicata correttamente"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping("/signature")
    public ResponseEntity<Void> signature(@RequestBody RestRequest<PaperlessDocumentSignatureDtoReq> request) {
        PaperlessDocumentSignature documentSignature = PaperlessDocumentSignatureMapper.mapToDomain(request);
        notifyPaperlessDocumentSignatureUseCase.notifyDocumentSignature(documentSignature);
        return ResponseEntity.ok().build();
    }
}
