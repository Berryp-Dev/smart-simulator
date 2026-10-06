package com.capgemini.smart.simulator.paperless.document.signature.adapter.out.mq;

import com.capgemini.smart.simulator.core.mq.MqPublisherService;
import com.capgemini.smart.simulator.paperless.config.PaperlessMqProperties;
import com.capgemini.smart.simulator.paperless.document.signature.adapter.out.mq.message.InvioAggiornamentoStato;
import com.capgemini.smart.simulator.paperless.document.signature.application.port.out.PublishDocumentStatusUpdatePort;
import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentStatusUpdate;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

/**
 * Adapter che marshalla {@link InvioAggiornamentoStato} in XML e lo pubblica
 * sulla coda IBM MQ dedicata allo scenario Document Signature, tramite
 * {@link MqPublisherService#sendRawText(String, String)} (testo grezzo, non
 * JSON). Porta/adapter/chiave queueMap dedicati: non riusa quelli di
 * Practice Configure.
 */
@Component
public class PaperlessDocumentSignatureMqAdapter implements PublishDocumentStatusUpdatePort {

    private static final String SCENARIO_QUEUE_KEY = "paperlessDocumentSignature";
    private static final String FIXED_CODICE_FISCALE = "XXXXXXXXXXXXXXXX";

    private final MqPublisherService mqPublisherService;
    private final PaperlessMqProperties paperlessMqProperties;

    public PaperlessDocumentSignatureMqAdapter(MqPublisherService mqPublisherService,
                                               PaperlessMqProperties paperlessMqProperties) {
        this.mqPublisherService = mqPublisherService;
        this.paperlessMqProperties = paperlessMqProperties;
    }

    @Override
    public void publishDocumentStatusUpdate(PaperlessDocumentStatusUpdate documentStatusUpdate) {
        InvioAggiornamentoStato statusUpdateMessage = mapToMessage(documentStatusUpdate);
        String xmlPayload = marshalToXml(statusUpdateMessage);
        String queueName = resolveQueueName();
        mqPublisherService.sendRawText(queueName, xmlPayload);
    }

    private InvioAggiornamentoStato mapToMessage(PaperlessDocumentStatusUpdate documentStatusUpdate) {
        InvioAggiornamentoStato statusUpdateMessage = new InvioAggiornamentoStato();
        statusUpdateMessage.setIdPratica(documentStatusUpdate.practiceId());
        statusUpdateMessage.setIdExternal(documentStatusUpdate.externalId());
        statusUpdateMessage.setAmbitoPratica(documentStatusUpdate.practiceScope());
        statusUpdateMessage.setIdDoc(documentStatusUpdate.documentId());
        statusUpdateMessage.setStato(documentStatusUpdate.status().name());
        statusUpdateMessage.setCodiceFiscale(FIXED_CODICE_FISCALE);
        return statusUpdateMessage;
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
            throw new IllegalStateException("Errore nel marshalling XML di InvioAggiornamentoStato (document.signature)", ex);
        }
    }
}
