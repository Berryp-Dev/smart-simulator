package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Header
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class Header {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime timestamp;

  private @Nullable String trackId;

  private @Nullable String applicationId;

  private @Nullable String operationCode;

  private @Nullable String channel;

  private @Nullable String terminal;

  private @Nullable String companyCode;

  private @Nullable String branchCode;

  private @Nullable String user;

  private @Nullable String profile;

  private @Nullable String logicalChannel;

  private @Nullable String idempotencyId;

  private @Nullable String transactionId;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header timestamp(@Nullable OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * The date time in RFC 3339 format.
   * @return timestamp
   */
  @Valid 
  @Schema(name = "timestamp", example = "2024-01-18T15:04:05Z", description = "The date time in RFC 3339 format.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public @Nullable OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@Nullable OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header trackId(@Nullable String trackId) {
    this.trackId = trackId;
    return this;
  }

  /**
   * Get trackId
   * @return trackId
   */
  
  @Schema(name = "trackId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("trackId")
  public @Nullable String getTrackId() {
    return trackId;
  }

  public void setTrackId(@Nullable String trackId) {
    this.trackId = trackId;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header applicationId(@Nullable String applicationId) {
    this.applicationId = applicationId;
    return this;
  }

  /**
   * Get applicationId
   * @return applicationId
   */
  
  @Schema(name = "applicationId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("applicationId")
  public @Nullable String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(@Nullable String applicationId) {
    this.applicationId = applicationId;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header operationCode(@Nullable String operationCode) {
    this.operationCode = operationCode;
    return this;
  }

  /**
   * Get operationCode
   * @return operationCode
   */
  
  @Schema(name = "operationCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("operationCode")
  public @Nullable String getOperationCode() {
    return operationCode;
  }

  public void setOperationCode(@Nullable String operationCode) {
    this.operationCode = operationCode;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header channel(@Nullable String channel) {
    this.channel = channel;
    return this;
  }

  /**
   * Get channel
   * @return channel
   */
  
  @Schema(name = "channel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("channel")
  public @Nullable String getChannel() {
    return channel;
  }

  public void setChannel(@Nullable String channel) {
    this.channel = channel;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header terminal(@Nullable String terminal) {
    this.terminal = terminal;
    return this;
  }

  /**
   * Get terminal
   * @return terminal
   */
  
  @Schema(name = "terminal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("terminal")
  public @Nullable String getTerminal() {
    return terminal;
  }

  public void setTerminal(@Nullable String terminal) {
    this.terminal = terminal;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header companyCode(@Nullable String companyCode) {
    this.companyCode = companyCode;
    return this;
  }

  /**
   * Get companyCode
   * @return companyCode
   */
  
  @Schema(name = "companyCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("companyCode")
  public @Nullable String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(@Nullable String companyCode) {
    this.companyCode = companyCode;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header branchCode(@Nullable String branchCode) {
    this.branchCode = branchCode;
    return this;
  }

  /**
   * Get branchCode
   * @return branchCode
   */
  
  @Schema(name = "branchCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("branchCode")
  public @Nullable String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(@Nullable String branchCode) {
    this.branchCode = branchCode;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header user(@Nullable String user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
   */
  
  @Schema(name = "user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public @Nullable String getUser() {
    return user;
  }

  public void setUser(@Nullable String user) {
    this.user = user;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header profile(@Nullable String profile) {
    this.profile = profile;
    return this;
  }

  /**
   * Get profile
   * @return profile
   */
  
  @Schema(name = "profile", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("profile")
  public @Nullable String getProfile() {
    return profile;
  }

  public void setProfile(@Nullable String profile) {
    this.profile = profile;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header logicalChannel(@Nullable String logicalChannel) {
    this.logicalChannel = logicalChannel;
    return this;
  }

  /**
   * Get logicalChannel
   * @return logicalChannel
   */
  
  @Schema(name = "logicalChannel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("logicalChannel")
  public @Nullable String getLogicalChannel() {
    return logicalChannel;
  }

  public void setLogicalChannel(@Nullable String logicalChannel) {
    this.logicalChannel = logicalChannel;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header idempotencyId(@Nullable String idempotencyId) {
    this.idempotencyId = idempotencyId;
    return this;
  }

  /**
   * Get idempotencyId
   * @return idempotencyId
   */
  
  @Schema(name = "idempotencyId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("idempotencyId")
  public @Nullable String getIdempotencyId() {
    return idempotencyId;
  }

  public void setIdempotencyId(@Nullable String idempotencyId) {
    this.idempotencyId = idempotencyId;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public Header transactionId(@Nullable String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * Get transactionId
   * @return transactionId
   */
  
  @Schema(name = "transactionId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("transactionId")
  public @Nullable String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(@Nullable String transactionId) {
    this.transactionId = transactionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Header header = (Header) o;
    return Objects.equals(this.timestamp, header.timestamp) &&
        Objects.equals(this.trackId, header.trackId) &&
        Objects.equals(this.applicationId, header.applicationId) &&
        Objects.equals(this.operationCode, header.operationCode) &&
        Objects.equals(this.channel, header.channel) &&
        Objects.equals(this.terminal, header.terminal) &&
        Objects.equals(this.companyCode, header.companyCode) &&
        Objects.equals(this.branchCode, header.branchCode) &&
        Objects.equals(this.user, header.user) &&
        Objects.equals(this.profile, header.profile) &&
        Objects.equals(this.logicalChannel, header.logicalChannel) &&
        Objects.equals(this.idempotencyId, header.idempotencyId) &&
        Objects.equals(this.transactionId, header.transactionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, trackId, applicationId, operationCode, channel, terminal, companyCode, branchCode, user, profile, logicalChannel, idempotencyId, transactionId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Header {\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    trackId: ").append(toIndentedString(trackId)).append("\n");
    sb.append("    applicationId: ").append(toIndentedString(applicationId)).append("\n");
    sb.append("    operationCode: ").append(toIndentedString(operationCode)).append("\n");
    sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
    sb.append("    terminal: ").append(toIndentedString(terminal)).append("\n");
    sb.append("    companyCode: ").append(toIndentedString(companyCode)).append("\n");
    sb.append("    branchCode: ").append(toIndentedString(branchCode)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    profile: ").append(toIndentedString(profile)).append("\n");
    sb.append("    logicalChannel: ").append(toIndentedString(logicalChannel)).append("\n");
    sb.append("    idempotencyId: ").append(toIndentedString(idempotencyId)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

