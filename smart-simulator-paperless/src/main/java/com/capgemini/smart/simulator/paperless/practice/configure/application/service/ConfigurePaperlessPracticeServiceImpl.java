package com.capgemini.smart.simulator.paperless.practice.configure.application.service;

import com.capgemini.smart.simulator.core.exception.ValidationException;
import com.capgemini.smart.simulator.core.util.RandomUtil;
import com.capgemini.smart.simulator.core.validation.ValidationResult;
import com.capgemini.smart.simulator.paperless.practice.configure.application.port.in.ConfigurePaperlessPracticeService;
import com.capgemini.smart.simulator.paperless.practice.configure.application.port.out.PublishStatusUpdatePort;
import com.capgemini.smart.simulator.paperless.practice.configure.application.validation.PaperlessPracticeConfigureValidator;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeConfigureScenarioCode;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeStatusCode;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfiguration;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfigureResult;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeStatusUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementazione dello scenario Practice Configure: valida la request,
 * costruisce l'aggiornamento di stato di dominio e lo pubblica tramite la
 * port in uscita. La conversione nel messaggio XML e' compito dell'adapter.
 */
@Service
public class ConfigurePaperlessPracticeServiceImpl implements ConfigurePaperlessPracticeService {

    private static final int PRACTICE_ID_LENGTH = 10;
    private static final int PROCESS_ID_LENGTH = 3;

    @Autowired
    private PaperlessPracticeConfigureValidator practiceConfigureValidator;
    @Autowired
    private PublishStatusUpdatePort publishStatusUpdatePort;

    @Override
    public PaperlessPracticeConfigureResult configurePractice(PaperlessPracticeConfiguration practiceConfiguration) {
        ValidationResult validationResult = practiceConfigureValidator.validate(practiceConfiguration);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult);
        }
        PaperlessPracticeStatusCode status = resolveStato(practiceConfiguration.scenarioCode());
        PaperlessPracticeStatusUpdate statusUpdate = buildStatusUpdate(practiceConfiguration, status);
        publishStatusUpdatePort.publishStatusUpdate(statusUpdate);
        return new PaperlessPracticeConfigureResult(status);
    }

    private PaperlessPracticeStatusUpdate buildStatusUpdate(PaperlessPracticeConfiguration practiceConfiguration,
                                                            PaperlessPracticeStatusCode status) {
        return new PaperlessPracticeStatusUpdate(
                RandomUtil.generateNumericString(PRACTICE_ID_LENGTH),
                RandomUtil.generateNumericString(PROCESS_ID_LENGTH),
                practiceConfiguration.practiceIdOmnichannel(),
                practiceConfiguration.scope(),
                resolveMotivoBlocco(practiceConfiguration),
                status);
    }

    private String resolveMotivoBlocco(PaperlessPracticeConfiguration practiceConfiguration) {
        if (practiceConfiguration.scenarioCode() != PaperlessPracticeConfigureScenarioCode.PRACTICE_LOCKED) {
            return null;
        }
        return "Firmatario " + practiceConfiguration.customerDenomination() + " privo di consenso privacy";
    }

    private PaperlessPracticeStatusCode resolveStato(PaperlessPracticeConfigureScenarioCode scenarioCode) {
        return switch (scenarioCode) {
            case SIGNATORIES_WAITING -> PaperlessPracticeStatusCode.ATTESA_FIRMATARI;
            case PRACTICE_LOCKED -> PaperlessPracticeStatusCode.PRATICA_BLOCCATA;
            case PRACTICE_UNLOCKED -> PaperlessPracticeStatusCode.PRATICA_SBLOCCATA;
            case CONTEXT_SIGNATURE_WAITING -> PaperlessPracticeStatusCode.ATTESA_CONTESTO_FIRMA;
            case CUSTOMER_SIGNATURE_WAITING -> PaperlessPracticeStatusCode.ATTESA_FIRMA_CLIENTE;
        };
    }
}
