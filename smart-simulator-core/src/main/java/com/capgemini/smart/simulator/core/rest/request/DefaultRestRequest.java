/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnore
 */
package com.capgemini.smart.simulator.core.rest.request;

import com.capgemini.smart.simulator.core.context.HermesHeaderDTO;
import com.capgemini.smart.simulator.core.request.BaseRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DefaultRestRequest<BODY>
extends BaseRequest<HermesHeaderDTO, BODY> {
    public DefaultRestRequest() {
        this.header = new HermesHeaderDTO();
    }

    public DefaultRestRequest(HermesHeaderDTO header, BODY payload) {
        this.payload = payload;
        this.header = header;
    }

    @Deprecated
    @JsonIgnore
    public BODY getBody() {
        return this.getPayload();
    }
}

