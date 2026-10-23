package com.capgemini.smart.simulator.documentviewer.document.getgenerated.adapter.out.decryption;

import com.bnpp.tripledes.URLEncUtils;
import com.capgemini.smart.simulator.core.logging.RestLoggingFilter;
import com.capgemini.smart.simulator.documentviewer.config.DocumentViewerProperties;
import com.capgemini.smart.simulator.documentviewer.document.getgenerated.application.port.out.DecryptBnlTokenPort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Adapter della port {@link DecryptBnlTokenPort}: decripta bnlToken con AES
 * (libreria com.capgemini:encryption, {@code URLEncUtils.decodeAES}),
 * usando la chiave configurata in {@code token-decryption-key}.
 */
@Component
public class BnlTokenDecryptionAdapter implements DecryptBnlTokenPort {
	
	private static final Logger log = LoggerFactory.getLogger(BnlTokenDecryptionAdapter.class);
	
    @Autowired
    private DocumentViewerProperties properties;

    @Override
    public String decryptToken(String bnlToken) {
    	log.info("PRE-DECRIPT REST: , bnlToken={}, key={}", bnlToken,properties.getTokenDecryptionKey());
    	
        String decodeAES = URLEncUtils.decodeAES(bnlToken, properties.getTokenDecryptionKey());
        log.info("POST-DECRIPT REST: , decodeAES={}" , decodeAES);
        
        return decodeAES;
        
        
    }
}
