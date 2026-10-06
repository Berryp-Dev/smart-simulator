package com.capgemini.smart.simulator.core.soap;

import jakarta.annotation.PostConstruct;
import org.apache.cxf.Bus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Configurazione CXF condivisa da tutti i moduli sottosistema che espongono
 * un endpoint SOAP.
 *
 * Ogni modulo:
 *  1. genera gli stub Java dal proprio WSDL originale (contract-first) con
 *     il plugin cxf-codegen-plugin, configurato nel pom del modulo;
 *  2. implementa l'interfaccia SEI generata;
 *  3. pubblica l'endpoint, tipicamente cosi':
 *
 * <pre>
 * {@code
 * @Configuration
 * public class PaperlessSoapConfig {
 *
 *     @Autowired
 *     private Bus bus;
 *
 *     @Bean
 *     public Endpoint paperlessEndpoint() {
 *         EndpointImpl endpoint = new EndpointImpl(bus, new PaperlessServiceImpl());
 *         endpoint.publish("/paperless");
 *         return endpoint;
 *     }
 * }
 * }
 * </pre>
 */
@Configuration
public class CxfCommonConfig {

    @Autowired
    private Bus bus;

    @PostConstruct
    public void configureLogging() {
        bus.getInInterceptors().add(new LoggingInInterceptor());
        bus.getOutInterceptors().add(new LoggingOutInterceptor());
    }
}
