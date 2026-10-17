package com.capgemini.smart.simulator.paperless.document.signature.application.service;

import com.capgemini.smart.simulator.core.exception.ValidationException;
import com.capgemini.smart.simulator.core.util.RandomUtil;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.paperless.document.signature.application.port.in.NotifyPaperlessDocumentSignatureService;
import com.capgemini.smart.simulator.paperless.document.signature.application.port.out.PublishDocumentStatusUpdatePort;
import com.capgemini.smart.simulator.paperless.document.signature.application.validation.PaperlessDocumentSignatureValidator;
import com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode;
import com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureStatusCode;
import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentSignature;
import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentStatusUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementazione dello scenario Document Signature: valida la request,
 * poi pubblica tramite la port in uscita un aggiornamento di stato di dominio
 * per ciascun documento della pratica. La conversione nel messaggio XML e'
 * compito dell'adapter.
 */
@Service
public class NotifyPaperlessDocumentSignatureServiceImpl implements NotifyPaperlessDocumentSignatureService {

    private static final int PRACTICE_ID_LENGTH = 10;

    @Autowired
    private PaperlessDocumentSignatureValidator documentSignatureValidator;
    @Autowired
    private PublishDocumentStatusUpdatePort publishDocumentStatusUpdatePort;

    @Override
    public void notifyDocumentSignature(PaperlessDocumentSignature documentSignature) {
        ValidationResult validationResult = documentSignatureValidator.validate(documentSignature);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult);
        }
        String practiceId = RandomUtil.generateNumericString(PRACTICE_ID_LENGTH);
        Integer deviantElementPosition = resolveDeviantElementPosition(documentSignature);
        publishDocumentStatusMessages(documentSignature, practiceId, deviantElementPosition);
    }

    private Integer resolveDeviantElementPosition(PaperlessDocumentSignature documentSignature) {
        if (documentSignature.scenarioCode() == PaperlessDocumentSignatureScenarioCode.ALL_SIGNED) {
            return null;
        }
        int documentCount = documentSignature.documentIds().size();
        return ThreadLocalRandom.current().nextInt(documentCount) + 1;
    }

    private void publishDocumentStatusMessages(PaperlessDocumentSignature documentSignature, String practiceId,
                                               Integer deviantElementPosition) {
        List<Long> documentIds = documentSignature.documentIds();
        for (int documentIndex = 0; documentIndex < documentIds.size(); documentIndex++) {
            int currentPosition = documentIndex + 1;
            PaperlessDocumentSignatureStatusCode documentStatus = resolveDocumentStatus(documentSignature.scenarioCode(),
                    currentPosition, deviantElementPosition);
            PaperlessDocumentStatusUpdate documentStatusUpdate = buildDocumentStatusUpdate(documentSignature,
                    documentIds.get(documentIndex), practiceId, documentStatus);
            publishDocumentStatusUpdatePort.publishDocumentStatusUpdate(documentStatusUpdate);
        }
    }

    private PaperlessDocumentSignatureStatusCode resolveDocumentStatus(PaperlessDocumentSignatureScenarioCode scenarioCode,
                                                                       int currentPosition,
                                                                       Integer deviantElementPosition) {
        if (scenarioCode == PaperlessDocumentSignatureScenarioCode.ALL_SIGNED || currentPosition != deviantElementPosition) {
            return PaperlessDocumentSignatureStatusCode.FIRMATO;
        }
        return scenarioCode == PaperlessDocumentSignatureScenarioCode.PARTIAL_SIGNATURE
                ? PaperlessDocumentSignatureStatusCode.PARZIALE
                : PaperlessDocumentSignatureStatusCode.ERRORE_FIRMA;
    }

    private PaperlessDocumentStatusUpdate buildDocumentStatusUpdate(PaperlessDocumentSignature documentSignature,
                                                                    Long documentId,
                                                                    String practiceId,
                                                                    PaperlessDocumentSignatureStatusCode documentStatus) {
        return new PaperlessDocumentStatusUpdate(practiceId, documentSignature.practiceIdOmnichannel(),
                documentSignature.scope(), documentId, documentStatus);
    }
}
