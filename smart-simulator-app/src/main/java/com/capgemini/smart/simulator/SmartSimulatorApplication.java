package com.capgemini.smart.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Unico punto di ingresso dell'applicazione. Dichiarata nel package radice
 * com.capgemini.smart.simulator cosi' il component scan di default di
 * Spring Boot copre sia com.capgemini.smart.simulator.core.* (componenti
 * condivisi: GlobalExceptionHandler, MqPublisherService, DefaultsResolver,
 * ecc.) sia il package di ogni modulo sottosistema (es.
 * com.capgemini.smart.simulator.paperless.*).
 */
@SpringBootApplication
public class SmartSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartSimulatorApplication.class, args);
    }
}
