package com.capgemini.smart.simulator.paperless.document.signature.application.port.in;

import com.capgemini.smart.simulator.paperless.document.signature.domain.model.PaperlessDocumentSignature;

/**
 * Driving port dello scenario Document Signature.
 */
public interface NotifyPaperlessDocumentSignatureService {

    void notifyDocumentSignature(PaperlessDocumentSignature documentSignature);
}
