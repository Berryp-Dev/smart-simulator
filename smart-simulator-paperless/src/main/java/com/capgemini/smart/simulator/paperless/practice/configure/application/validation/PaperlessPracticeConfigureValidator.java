package com.capgemini.smart.simulator.paperless.practice.configure.application.validation;

import com.capgemini.smart.simulator.core.util.StringUtil;
import com.capgemini.smart.simulator.core.validation.ScenarioValidator;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfiguration;
import org.springframework.stereotype.Component;

/**
 * Validator dello scenario Practice Configure: esegue tutti i controlli
 * previsti e raccoglie ogni errore riscontrato (non fail-fast). Il controllo
 * su scenarioCode verifica solo la presenza: il controllo "valore ammesso"
 * e' ormai automatico (deserializzazione Jackson + GlobalExceptionHandler
 * del core).
 */
@Component
public class PaperlessPracticeConfigureValidator implements ScenarioValidator<PaperlessPracticeConfiguration> {

    @Override
    public ValidationResult validate(PaperlessPracticeConfiguration practiceConfigure) {
        ValidationResult.Builder resultBuilder = ValidationResult.builder();
        validatePracticeIdOmnichannel(practiceConfigure.practiceIdOmnichannel(), resultBuilder);
        validateCustomerDenomination(practiceConfigure.customerDenomination(), resultBuilder);
        validateScenarioCode(practiceConfigure.scenarioCode(), resultBuilder);
        validateScope(practiceConfigure.scope(), resultBuilder);
        return resultBuilder.build();
    }

    private void validatePracticeIdOmnichannel(String practiceIdOmnichannel, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(practiceIdOmnichannel)) {
            resultBuilder.insertError("practiceIdOmnichannel", "Campo obbligatorio");
        }
    }

    private void validateCustomerDenomination(String customerDenomination, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(customerDenomination)) {
            resultBuilder.insertError("customerDenomination", "Campo obbligatorio");
        }
    }

    private void validateScenarioCode(PaperlessPracticeConfigureScenarioCode scenarioCode,
                                      ValidationResult.Builder resultBuilder) {
        if (scenarioCode == null) {
            resultBuilder.insertError("scenarioCode", "Campo obbligatorio");
        }
    }

    private void validateScope(String scope, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(scope)) {
            resultBuilder.insertError("scope", "Campo obbligatorio");
        }
    }
}
