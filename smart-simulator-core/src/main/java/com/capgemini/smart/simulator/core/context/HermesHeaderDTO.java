/*
 * Decompiled with CFR 0.152.
 */
package com.capgemini.smart.simulator.core.context;

public class HermesHeaderDTO
implements DefaultContext {
    private String timestamp;
    private String transactionId;
    private String trackId;
    private String applicationId;
    private String requestId;
    private String operationCode;
    private String channel;
    private String logicalChannel;
    private String terminal;
    private String companyCode;
    private String branchCode;
    private String user;
    private String profile;
    private String idempotencyId;
    private String locale;

    @Override
    public String getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String getTransactionId() {
        return this.transactionId;
    }

    @Override
    public String getTrackId() {
        return this.trackId;
    }

    @Override
    public String getApplicationId() {
        return this.applicationId;
    }

    @Override
    public String getRequestId() {
        return this.requestId;
    }

    @Override
    public String getOperationCode() {
        return this.operationCode;
    }

    @Override
    public String getChannel() {
        return this.channel;
    }

    @Override
    public String getTerminal() {
        return this.terminal;
    }

    @Override
    public String getCompanyCode() {
        return this.companyCode;
    }

    @Override
    public String getBranchCode() {
        return this.branchCode;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public String getProfile() {
        return this.profile;
    }

    @Override
    public String getIdempotencyId() {
        return this.idempotencyId;
    }

    @Override
    public String getLocale() {
        return this.locale;
    }

    @Override
    public String getLogicalChannel() {
        return this.logicalChannel;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setLogicalChannel(String logicalChannel) {
        this.logicalChannel = logicalChannel;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setIdempotencyId(String idempotencyId) {
        this.idempotencyId = idempotencyId;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}

