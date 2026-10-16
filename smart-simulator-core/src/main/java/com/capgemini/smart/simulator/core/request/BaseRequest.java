/*
 * Decompiled with CFR 0.152.
 */
package com.capgemini.smart.simulator.core.request;

public abstract class BaseRequest<HEADER, BODY> {
    protected HEADER header;
    protected BODY payload;

    public HEADER getHeader() {
        return this.header;
    }

    public BODY getPayload() {
        return this.payload;
    }

    public void setHeader(HEADER header) {
        this.header = header;
    }

    public void setPayload(BODY payload) {
        this.payload = payload;
    }
}

