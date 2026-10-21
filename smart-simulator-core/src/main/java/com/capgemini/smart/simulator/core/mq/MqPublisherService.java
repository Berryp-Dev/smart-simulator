package com.capgemini.smart.simulator.core.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Publisher generico riutilizzabile da tutti i moduli sottosistema per
 * l'invio di messaggi su code IBM MQ.
 */
@Service
public class MqPublisherService {

    private static final Logger log = LoggerFactory.getLogger(MqPublisherService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Invia un payload (serializzato in JSON dal converter configurato in
     * {@link JmsConfig}) sulla coda indicata.
     */
    public void send(String queueName, Object payload) {
        log.debug("Invio messaggio su coda MQ '{}'", queueName);
        try {
            jmsTemplate.convertAndSend(queueName, payload);
        } catch (Exception ex) {
            log.error("Errore invio messaggio su coda '{}': {}", queueName, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * Invia un testo grezzo (es. un payload XML gia' marshallato) sulla coda
     * indicata, come JMS TextMessage, senza passare dal converter JSON
     * configurato in {@link JmsConfig}. Da usare quando il formato concordato
     * con il sistema a valle non e' JSON (es. messaggi XML da classi JAXB).
     * <p>
     * Ogni scrittura e' tracciata con il prefisso "Scrittura MQ": prima
     * dell'invio (INFO, nome fisico della coda e payload completo), dopo
     * l'invio riuscito (INFO, solo coda) e in caso di errore (ERROR, coda,
     * payload ed eccezione con stack trace; l'eccezione e' poi rilanciata
     * invariata).
     */
    public void sendRawText(String queueName, String rawText) {
        log.info("Scrittura MQ - invio: coda='{}', payload={}", queueName, rawText);
        try {
            jmsTemplate.send(queueName, session -> session.createTextMessage(rawText));
        } catch (Exception ex) {
            log.error("Scrittura MQ - esito negativo: coda='{}', payload={}, errore: {}",
                    queueName, rawText, ex.getMessage(), ex);
            throw ex;
        }
        log.info("Scrittura MQ - esito positivo: coda='{}'", queueName);
    }
}
