package com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.validation;

import com.capgemini.smart.simulator.core.util.StringUtil;
import com.capgemini.smart.simulator.core.validation.ScenarioValidator;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.domain.model.GetGeneratedDocumentCriteria;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validator del servizio getGenerated: esegue tutti i controlli previsti e
 * raccoglie ogni errore riscontrato (non fail-fast). I campi sono indicati
 * con il nome del contratto REST.
 */
@Component
public class RetrieveGetGeneratedDocumentValidator implements ScenarioValidator<GetGeneratedDocumentCriteria> {

    private static final String FIELD_APP_NAME = "appName";
    private static final String FIELD_TRACKING_ID = "trackingId";
    private static final String FIELD_CALL_UNSEAL = "callUnseal";
    private static final String FIELD_BNL_TOKEN = "bnlToken";
    private static final String MESSAGE_REQUIRED = "Campo obbligatorio";

    @Override
    public ValidationResult validate(GetGeneratedDocumentCriteria criteria) {
        ValidationResult.Builder resultBuilder = ValidationResult.builder();
        validateAppName(criteria.appName(), resultBuilder);
        validateTrackingId(criteria.trackingId(), resultBuilder);
        validateCallUnseal(criteria.callUnseal(), resultBuilder);
        validateBnlToken(criteria.bnlToken(), resultBuilder);
        return resultBuilder.build();
    }

    private void validateAppName(String appName, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(appName)) {
            resultBuilder.insertError(FIELD_APP_NAME, MESSAGE_REQUIRED);
        }
    }

    private void validateTrackingId(String trackingId, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(trackingId)) {
            resultBuilder.insertError(FIELD_TRACKING_ID, MESSAGE_REQUIRED);
            return;
        }
        try {
            UUID.fromString(trackingId);
        } catch (IllegalArgumentException e) {
            resultBuilder.insertError(FIELD_TRACKING_ID, "Valore non valido: formato UUID atteso");
        }
    }

    private void validateCallUnseal(String callUnseal, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(callUnseal)) {
            resultBuilder.insertError(FIELD_CALL_UNSEAL, MESSAGE_REQUIRED);
        }
    }

    private void validateBnlToken(String bnlToken, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(bnlToken)) {
            resultBuilder.insertError(FIELD_BNL_TOKEN, MESSAGE_REQUIRED);
        }
    }
}
