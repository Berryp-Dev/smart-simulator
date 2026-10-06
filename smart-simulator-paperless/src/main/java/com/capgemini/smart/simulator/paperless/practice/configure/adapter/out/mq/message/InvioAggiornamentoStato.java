package com.capgemini.smart.simulator.paperless.practice.configure.adapter.out.mq.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Messaggio XML (JAXB) pubblicato su IBM MQ a seguito dello scenario
 * Practice Configure. MotivoBlocco non viene marshallato (resta assente
 * nell'XML) quando il relativo valore e' null.
 */
@XmlRootElement(name = "InvioAggiornamentoStato")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvioAggiornamentoStato {

    @XmlElement(name = "IdPratica")
    private String idPratica;

    @XmlElement(name = "DM_IdProcesso")
    private String dmIdProcesso;

    @XmlElement(name = "IdExternal")
    private String idExternal;

    @XmlElement(name = "AmbitoPratica")
    private String ambitoPratica;

    @XmlElement(name = "MotivoBlocco")
    private String motivoBlocco;

    @XmlElement(name = "Stato")
    private String stato;

    public String getIdPratica() {
        return idPratica;
    }

    public void setIdPratica(String idPratica) {
        this.idPratica = idPratica;
    }

    public String getDmIdProcesso() {
        return dmIdProcesso;
    }

    public void setDmIdProcesso(String dmIdProcesso) {
        this.dmIdProcesso = dmIdProcesso;
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

    public String getMotivoBlocco() {
        return motivoBlocco;
    }

    public void setMotivoBlocco(String motivoBlocco) {
        this.motivoBlocco = motivoBlocco;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
