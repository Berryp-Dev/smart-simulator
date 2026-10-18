package com.capgemini.smart.simulator.documentviewer.document.application.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.capgemini.smart.simulator.core.validation.ValidationError;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.documentviewer.document.domain.model.GetStaticDocumentSearchCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class RetrieveGetStaticDocumentValidatorTest {

    private static final String REQUIRED = "Campo obbligatorio";
    private static final String NOT_ALPHANUMERIC = "Valore non valido: sono ammessi solo caratteri alfanumerici";

    private final RetrieveGetStaticDocumentValidator validator = new RetrieveGetStaticDocumentValidator();

    @Test
    void validate_validProductCode_isValid() {
        ValidationResult result = validator.validate(new GetStaticDocumentSearchCriteria("12234"));

        assertTrue(result.isValid());
    }

    @Test
    void validate_mixedCaseAlphanumeric_isValid() {
        ValidationResult result = validator.validate(new GetStaticDocumentSearchCriteria("Ab12Cd"));

        assertTrue(result.isValid());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void validate_missingOrBlank_requiredError(String productCode) {
        ValidationResult result = validator.validate(new GetStaticDocumentSearchCriteria(productCode));

        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertEquals(new ValidationError("productCode", REQUIRED), result.getErrors().get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"../x", "a/b", "a\\b", "a.b", "a b", "12234.pdf"})
    void validate_notAlphanumeric_formatError(String productCode) {
        ValidationResult result = validator.validate(new GetStaticDocumentSearchCriteria(productCode));

        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertEquals(new ValidationError("productCode", NOT_ALPHANUMERIC), result.getErrors().get(0));
    }
}
