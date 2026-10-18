package com.capgemini.smart.simulator.documentviewer.staticdoc.application.validation;

import com.capgemini.smart.simulator.core.util.StringUtil;
import com.capgemini.smart.simulator.core.validation.ScenarioValidator;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Validator dello scenario Static Doc (retrieve): esegue tutti i controlli
 * previsti e raccoglie ogni errore riscontrato (non fail-fast). Il campo e'
 * indicato con il nome del contratto REST (productCode).
 */
@Component
public class RetrieveDocumentValidator implements ScenarioValidator<DocumentSearchCriteria> {

    private static final String FIELD_PRODUCT_CODE = "productCode";
    private static final Pattern ALPHANUMERIC = Pattern.compile("^[A-Za-z0-9]+$");

    @Override
    public ValidationResult validate(DocumentSearchCriteria criteria) {
        ValidationResult.Builder resultBuilder = ValidationResult.builder();
        validateProductCode(criteria.productCode(), resultBuilder);
        return resultBuilder.build();
    }

    private void validateProductCode(String productCode, ValidationResult.Builder resultBuilder) {
        if (StringUtil.isBlank(productCode)) {
            resultBuilder.insertError(FIELD_PRODUCT_CODE, "Campo obbligatorio");
        } else if (!ALPHANUMERIC.matcher(productCode).matches()) {
            resultBuilder.insertError(FIELD_PRODUCT_CODE, "Valore non valido: sono ammessi solo caratteri alfanumerici");
        }
    }
}
