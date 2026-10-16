/*
 * Decompiled with CFR 0.152.
 */
package com.capgemini.smart.simulator.core.context;

public interface DefaultContext
extends Context {
    public String getTimestamp();

    public String getTransactionId();

    public String getTrackId();

    public String getApplicationId();

    public String getRequestId();

    public String getOperationCode();

    public String getChannel();

    public String getLogicalChannel();

    public String getTerminal();

    public String getCompanyCode();

    public String getBranchCode();

    public String getUser();

    public String getProfile();

    public String getIdempotencyId();

    public String getLocale();

    public static enum ContextKey {
        TIMESTAMP_CONTEXT_KEY,
        TRANSID_CONTEXT_KEY,
        TRACK_CONTEXT_KEY,
        APPLICATIONID_CONTEXT_KEY,
        REQUESTID_CONTEXT_KEY,
        OPERATIONCODE_CONTEXT_KEY,
        CHANNEL_CONTEXT_KEY,
        TERMINAL_CONTEXT_KEY,
        COMPANYCODE_CONTEXT_KEY,
        BRANCHCODE_CONTEXT_KEY,
        USER_CONTEXT_KEY,
        PROFILE_CONTEXT_KEY,
        LOGICALCHANNEL_CONTEXT_KEY,
        IDEMPOTENCYID_CONTEXT_KEY,
        LOCALE_CONTEXT_KEY,
        NULL_KEY;

    }
}

