package com.capgemini.smart.simulator.core.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Publisher generico riutilizzabile da tutti i moduli sottosistema per la
 * pubblicazione di eventi su Kafka. Il topic e' passato dal chiamante
 * (tipicamente risolto da configurazione specifica del modulo/operazione).
 */
@Service
public class KafkaPublisherService {

    private static final Logger log = LoggerFactory.getLogger(KafkaPublisherService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaPublisherService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Pubblica un messaggio su un topic Kafka usando una chiave di
     * correlazione (es. id operazione/entita') per garantire l'ordinamento
     * dei messaggi correlati sulla stessa partizione.
     */
    public CompletableFuture<SendResult<String, Object>> publish(String topic, String key, Object payload) {
        log.debug("Pubblicazione su topic Kafka '{}' con key '{}'", topic, key);
        return kafkaTemplate.send(topic, key, payload)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Errore pubblicazione su topic '{}': {}", topic, ex.getMessage(), ex);
                    } else {
                        log.debug("Messaggio pubblicato su '{}' - offset {}", topic,
                                result.getRecordMetadata().offset());
                    }
                });
    }
}
