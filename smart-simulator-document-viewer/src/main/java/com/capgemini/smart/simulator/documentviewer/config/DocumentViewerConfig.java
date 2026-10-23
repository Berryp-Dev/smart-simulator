package com.capgemini.smart.simulator.documentviewer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * Abilita il binding di {@link DocumentViewerProperties} da application.yml.
 */
@Configuration
@EnableConfigurationProperties(DocumentViewerProperties.class)
public class DocumentViewerConfig {

    private static final Logger log = LoggerFactory.getLogger(DocumentViewerConfig.class);

    @Autowired
    private DocumentViewerProperties properties;

    // DIAGNOSTICA TEMPORANEA (da rimuovere dopo l'indagine sit/dev): logga solo
    // l'hash SHA-256 di token-decryption-key, mai il valore in chiaro, per
    // confrontare la chiave effettivamente caricata tra ambienti.
    @PostConstruct
    public void logTokenDecryptionKeyHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(properties.getTokenDecryptionKey().getBytes(StandardCharsets.UTF_8));
            log.info("DIAGNOSTICA TEMPORANEA - hash SHA-256 di token-decryption-key: {}", HexFormat.of().formatHex(hash));
        } catch (NoSuchAlgorithmException e) {
            log.warn("DIAGNOSTICA TEMPORANEA - impossibile calcolare l'hash di token-decryption-key", e);
        }
    }
}
