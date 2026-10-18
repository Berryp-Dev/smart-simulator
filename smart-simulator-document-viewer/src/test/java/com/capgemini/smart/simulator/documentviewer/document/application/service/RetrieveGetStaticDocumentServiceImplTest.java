package com.capgemini.smart.simulator.documentviewer.document.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.capgemini.smart.simulator.core.exception.ValidationException;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.documentviewer.document.application.port.out.LoadGetStaticDocumentPort;
import com.capgemini.smart.simulator.documentviewer.document.application.validation.RetrieveGetStaticDocumentValidator;
import com.capgemini.smart.simulator.documentviewer.document.domain.exception.DocumentNotFoundException;
import com.capgemini.smart.simulator.documentviewer.document.domain.model.Document;
import com.capgemini.smart.simulator.documentviewer.document.domain.model.GetStaticDocumentSearchCriteria;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RetrieveGetStaticDocumentServiceImplTest {

    @Mock
    private RetrieveGetStaticDocumentValidator retrieveGetStaticDocumentValidator;
    @Mock
    private LoadGetStaticDocumentPort loadGetStaticDocumentPort;
    @InjectMocks
    private RetrieveGetStaticDocumentServiceImpl service;

    private final GetStaticDocumentSearchCriteria criteria = new GetStaticDocumentSearchCriteria("12234");

    @Test
    void getStaticDocument_validationFails_throwsValidationExceptionAndDoesNotLoad() {
        ValidationResult invalid = ValidationResult.builder().insertError("productCode", "Campo obbligatorio").build();
        when(retrieveGetStaticDocumentValidator.validate(criteria)).thenReturn(invalid);

        ValidationException exception = assertThrows(ValidationException.class, () -> service.getStaticDocument(criteria));

        assertEquals(1, exception.getErrors().size());
        verify(loadGetStaticDocumentPort, never()).getStaticDocument(any());
    }

    @Test
    void getStaticDocument_documentNotFound_throwsDocumentNotFoundException() {
        when(retrieveGetStaticDocumentValidator.validate(criteria)).thenReturn(ValidationResult.builder().build());
        when(loadGetStaticDocumentPort.getStaticDocument(criteria)).thenReturn(Optional.empty());

        assertThrows(DocumentNotFoundException.class, () -> service.getStaticDocument(criteria));
    }

    @Test
    void getStaticDocument_documentFound_returnsDocument() {
        Document document = new Document("12234", new byte[] {1, 2, 3});
        when(retrieveGetStaticDocumentValidator.validate(criteria)).thenReturn(ValidationResult.builder().build());
        when(loadGetStaticDocumentPort.getStaticDocument(criteria)).thenReturn(Optional.of(document));

        Document result = service.getStaticDocument(criteria);

        assertSame(document, result);
    }
}
