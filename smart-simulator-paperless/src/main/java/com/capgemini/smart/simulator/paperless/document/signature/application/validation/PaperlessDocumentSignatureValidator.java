package com.capgemini.smart.simulator.paperless.document.signature.application.validation;

import com.capgemini.smart.simulator.core.util.CollectionUtil;
import com.capgemini.smart.simulator.core.util.StringUtil;
import com.capgemini.smart.simulator.core.validation.ScenarioValidator;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.paperless.document.signature.domain.enums.PaperlessDocumentSignatureScenarioCode;
import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentSignature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Validator dello scenario Document Signature: esegue tutti i controlli
 * previsti e raccoglie ogni errore riscontrato (non fail-fast). Gli indici
 * degli elementi di documentIds (listDocuments nel contratto REST) nei field path sono 0-based, coerenti con
 * il path Jackson usato da GlobalExceptionHandler per gli errori di formato.
 */
@Component
public class PaperlessDocumentSignatureValidator implements ScenarioValidator<PaperlessDocumentSignature> {

    @Override
    public ValidationResult validate(PaperlessDocumentSignature documentSignature) {
        ValidationResult.Builder resultBuilder = ValidationResult.builder();
        validatePracticeIdOmnichannel(documentSignature.practiceIdOmnichannel(), resultBuilder);
        validateScope(documentSignature.scope(), resultBuilder);
        validateScenarioCode(documentSignature.scenarioCode(), resultBuilder);
        validateListDocuments(documentSignature.documentIds(), resultBuilder);
        return resultBuilder.build();
    }

    private void validatePracticeIdOmnichannel(String practiceIdOmnichannel, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(practiceIdOmnichannel)) {
            resultBuilder.insertError("practiceIdOmnichannel", "Campo obbligatorio");
        }
    }

    private void validateScope(String scope, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(scope)) {
            resultBuilder.insertError("scope", "Campo obbligatorio");
        }
    }

    private void validateScenarioCode(PaperlessDocumentSignatureScenarioCode scenarioCode,
                                      ValidationResult.Builder resultBuilder) {
        if (scenarioCode == null) {
            resultBuilder.insertError("scenarioCode", "Campo obbligatorio");
        }
    }

    private void validateListDocuments(List<Long> documentIds, ValidationResult.Builder resultBuilder) {
        if (CollectionUtil.isEmpty(documentIds)) {
            resultBuilder.insertError("listDocuments", "Campo obbligatorio e non vuoto");
            return;
        }
        for (int documentIndex = 0; documentIndex < documentIds.size(); documentIndex++) {
            if (documentIds.get(documentIndex) == null) {
                resultBuilder.insertError("listDocuments[" + documentIndex + "]", "Campo obbligatorio");
            }
        }
    }
}
