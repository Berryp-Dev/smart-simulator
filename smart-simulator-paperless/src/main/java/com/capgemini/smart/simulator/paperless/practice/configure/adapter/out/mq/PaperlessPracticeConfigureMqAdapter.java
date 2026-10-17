package com.capgemini.smart.simulator.paperless.practice.configure.adapter.out.mq;

import com.capgemini.smart.simulator.core.mq.MqPublisherService;
import com.capgemini.smart.simulator.paperless.config.PaperlessMqProperties;
import com.capgemini.smart.simulator.paperless.practice.configure.adapter.out.mq.message.InvioAggiornamentoStato;
import com.capgemini.smart.simulator.paperless.practice.configure.application.port.out.PublishStatusUpdatePort;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.enums.PaperlessPracticeStatusCode;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeStatusUpdate;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

/**
 * Adapter che marshalla {@link InvioAggiornamentoStato} in XML e lo pubblica
 * sulla coda IBM MQ dedicata allo scenario Practice Configure, tramite
 * {@link MqPublisherService#sendRawText(String, String)} (testo grezzo, non
 * JSON).
 */
@Component
public class PaperlessPracticeConfigureMqAdapter implements PublishStatusUpdatePort {

    private static final String SCENARIO_QUEUE_KEY = "paperlessPracticeConfigure";

    @Autowired
    private MqPublisherService mqPublisherService;
    @Autowired
    private PaperlessMqProperties paperlessMqProperties;

    @Override
    public void publishStatusUpdate(PaperlessPracticeStatusUpdate statusUpdate) {
        InvioAggiornamentoStato statusUpdateMessage = mapToMessage(statusUpdate);
        String xmlPayload = marshalToXml(statusUpdateMessage);
        String queueName = resolveQueueName();
        mqPublisherService.sendRawText(queueName, xmlPayload);
    }

    private InvioAggiornamentoStato mapToMessage(PaperlessPracticeStatusUpdate statusUpdate) {
        InvioAggiornamentoStato statusUpdateMessage = new InvioAggiornamentoStato();
        statusUpdateMessage.setIdPratica(statusUpdate.practiceId());
        statusUpdateMessage.setDmIdProcesso(statusUpdate.processId());
        statusUpdateMessage.setIdExternal(statusUpdate.externalId());
        statusUpdateMessage.setAmbitoPratica(statusUpdate.practiceScope());
        statusUpdateMessage.setMotivoBlocco(statusUpdate.lockReason());
        statusUpdateMessage.setStato(mapToMessageStatus(statusUpdate.status()));
        return statusUpdateMessage;
    }

    private String mapToMessageStatus(PaperlessPracticeStatusCode status) {
        return switch (status) {
            case ATTESA_FIRMATARI -> "ATTESA_FIRMATARI";
            case PRATICA_BLOCCATA -> "PRATICA_BLOCCATA";
            case PRATICA_SBLOCCATA -> "PRATICA_SBLOCCATA";
            case ATTESA_CONTESTO_FIRMA -> "ATTESA_CONTESTO_FIRMA";
            case ATTESA_FIRMA_CLIENTE -> "ATTESA_FIRMA_CLIENTE";
        };
    }

    private String resolveQueueName() {
        return paperlessMqProperties.getQueueMap().get(SCENARIO_QUEUE_KEY).getQueueName();
    }

    private String marshalToXml(InvioAggiornamentoStato statusUpdateMessage) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(InvioAggiornamentoStato.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(statusUpdateMessage, xmlWriter);
            return xmlWriter.toString();
        } catch (JAXBException ex) {
            throw new IllegalStateException("Errore nel marshalling XML di InvioAggiornamentoStato", ex);
        }
    }
}
