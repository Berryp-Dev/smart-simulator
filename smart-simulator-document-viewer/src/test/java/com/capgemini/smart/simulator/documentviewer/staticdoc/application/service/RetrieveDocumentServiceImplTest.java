package com.capgemini.smart.simulator.documentviewer.staticdoc.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.capgemini.smart.simulator.core.exception.ValidationException;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.documentviewer.staticdoc.application.port.out.LoadDocumentPort;
import com.capgemini.smart.simulator.documentviewer.staticdoc.application.validation.RetrieveDocumentValidator;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.exception.DocumentNotFoundException;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.staticdoc.domain.model.DocumentSearchCriteria;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RetrieveDocumentServiceImplTest {

    @Mock
    private RetrieveDocumentValidator retrieveDocumentValidator;
    @Mock
    private LoadDocumentPort loadDocumentPort;
    @InjectMocks
    private RetrieveDocumentServiceImpl service;

    private final DocumentSearchCriteria criteria = new DocumentSearchCriteria("12234");

    @Test
    void getDocument_validationFails_throwsValidationExceptionAndDoesNotLoad() {
        ValidationResult invalid = ValidationResult.builder().insertError("productCode", "Campo obbligatorio").build();
        when(retrieveDocumentValidator.validate(criteria)).thenReturn(invalid);

        ValidationException exception = assertThrows(ValidationException.class, () -> service.getDocument(criteria));

        assertEquals(1, exception.getErrors().size());
        verify(loadDocumentPort, never()).getDocument(any());
    }

    @Test
    void getDocument_documentNotFound_throwsDocumentNotFoundException() {
        when(retrieveDocumentValidator.validate(criteria)).thenReturn(ValidationResult.builder().build());
        when(loadDocumentPort.getDocument(criteria)).thenReturn(Optional.empty());

        assertThrows(DocumentNotFoundException.class, () -> service.getDocument(criteria));
    }

    @Test
    void getDocument_documentFound_returnsDocument() {
        Document document = new Document("12234", new byte[] {1, 2, 3});
        when(retrieveDocumentValidator.validate(criteria)).thenReturn(ValidationResult.builder().build());
        when(loadDocumentPort.getDocument(criteria)).thenReturn(Optional.of(document));

        Document result = service.getDocument(criteria);

        assertSame(document, result);
    }
}
