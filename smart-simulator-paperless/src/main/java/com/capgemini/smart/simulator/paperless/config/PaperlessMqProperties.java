package com.capgemini.smart.simulator.paperless.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Legge la mappa coda/broker per ogni scenario del modulo Paperless da
 * application.yml:
 *
 * <pre>
 * smart-simulator:
 *   paperless:
 *     mq:
 *       queue-map:
 *         paperlessPracticeConfigure:
 *           queue-name: DEV.QUEUE.2
 *           broker: nhMqBroker
 * </pre>
 * <p>
 * Il campo "broker" e' conservato come informazione di configurazione ma non
 * seleziona una connessione IBM MQ diversa: il modulo usa un'unica
 * connessione (configurata in ibm.mq.* e condivisa da JmsConfig/
 * MqPublisherService del core), coerente con l'infrastruttura attuale.
 */
@ConfigurationProperties(prefix = "smart-simulator.paperless.mq")
public class PaperlessMqProperties {

    private Map<String, QueueConfig> queueMap = new HashMap<>();

    public Map<String, QueueConfig> getQueueMap() {
        return queueMap;
    }

    public void setQueueMap(Map<String, QueueConfig> queueMap) {
        this.queueMap = queueMap;
    }

    /** Configurazione coda/broker dedicata a un singolo scenario. */
    public static class QueueConfig {

        private String queueName;
        private String broker;

        public String getQueueName() {
            return queueName;
        }

        public void setQueueName(String queueName) {
            this.queueName = queueName;
        }

        public String getBroker() {
            return broker;
        }

        public void setBroker(String broker) {
            this.broker = broker;
        }
    }
}
