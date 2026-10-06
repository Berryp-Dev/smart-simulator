package com.capgemini.smart.simulator.paperless.document.signature.adapter.out.mq.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Messaggio XML (JAXB) pubblicato su IBM MQ, uno per documento, a seguito
 * dello scenario Document Signature. Namespace e ordine dei campi coerenti
 * con il contratto del sistema Paperless originale per questo messaggio.
 */
@XmlRootElement(name = "InvioAggiornamentoStato", namespace = InvioAggiornamentoStato.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class InvioAggiornamentoStato {

    static final String NAMESPACE = "http://www.example.org/InvioAggiornamentoStato/";

    @XmlElement(name = "IdPratica", namespace = NAMESPACE)
    private String idPratica;

    @XmlElement(name = "IdExternal", namespace = NAMESPACE)
    private String idExternal;

    @XmlElement(name = "AmbitoPratica", namespace = NAMESPACE)
    private String ambitoPratica;

    @XmlElement(name = "IdDoc", namespace = NAMESPACE)
    private Long idDoc;

    @XmlElement(name = "Stato", namespace = NAMESPACE)
    private String stato;

    @XmlElement(name = "CodiceFiscale", namespace = NAMESPACE)
    private String codiceFiscale;

    public String getIdPratica() {
        return idPratica;
    }

    public void setIdPratica(String idPratica) {
        this.idPratica = idPratica;
    }

    public String getIdExternal() {
        return idExternal;
    }

    public void setIdExternal(String idExternal) {
        this.idExternal = idExternal;
    }

    public String getAmbitoPratica() {
        return ambitoPratica;
    }

    public void setAmbitoPratica(String ambitoPratica) {
        this.ambitoPratica = ambitoPratica;
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
