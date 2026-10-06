package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.NdgInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.ProdInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvAcntExtKey;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.StatusInfoRP;
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
 * Service account action information.
 */

@Schema(name = "SrvActnData", description = "Service account action information.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SrvActnData {

  private @Nullable String bankCod;

  private @Nullable String officeCod;

  private @Nullable String srvCod;

  private @Nullable String key;

  private @Nullable String externalKey;

  private @Nullable String legacyId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime tsStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime tsEnd;

  private @Nullable SrvAcntExtKey settlementSrvActn;

  private @Nullable StatusInfoRP statusInfo;

  private @Nullable NdgInfo ndgInfo;

  private @Nullable ProdInfo prodInfo;

  private @Nullable Object srvActnInfo;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData bankCod(@Nullable String bankCod) {
    this.bankCod = bankCod;
    return this;
  }

  /**
   * ABI Bank code.
   * @return bankCod
   */
  @Size(min = 5, max = 5) 
  @Schema(name = "bankCod", example = "01005", description = "ABI Bank code.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankCod")
  public @Nullable String getBankCod() {
    return bankCod;
  }

  public void setBankCod(@Nullable String bankCod) {
    this.bankCod = bankCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData officeCod(@Nullable String officeCod) {
    this.officeCod = officeCod;
    return this;
  }

  /**
   * Agency\\Office bank code.
   * @return officeCod
   */
  @Size(min = 5, max = 5) 
  @Schema(name = "officeCod", example = "12345", description = "Agency\\Office bank code.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("officeCod")
  public @Nullable String getOfficeCod() {
    return officeCod;
  }

  public void setOfficeCod(@Nullable String officeCod) {
    this.officeCod = officeCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData srvCod(@Nullable String srvCod) {
    this.srvCod = srvCod;
    return this;
  }

  /**
   * PLAB technical service code.
   * @return srvCod
   */
  
  @Schema(name = "srvCod", description = "PLAB technical service code.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("srvCod")
  public @Nullable String getSrvCod() {
    return srvCod;
  }

  public void setSrvCod(@Nullable String srvCod) {
    this.srvCod = srvCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData key(@Nullable String key) {
    this.key = key;
    return this;
  }

  /**
   * Key that identifies the preregistration account service or the account service under change process.
   * @return key
   */
  
  @Schema(name = "key", description = "Key that identifies the preregistration account service or the account service under change process.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("key")
  public @Nullable String getKey() {
    return key;
  }

  public void setKey(@Nullable String key) {
    this.key = key;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData externalKey(@Nullable String externalKey) {
    this.externalKey = externalKey;
    return this;
  }

  /**
   * Account service key on the legacy application. Can be null during preactivation phase (selling or change offer that requires new account service) but must be set before final activation. Always set for change/close processes on pre-existing account service.
   * @return externalKey
   */
  
  @Schema(name = "externalKey", description = "Account service key on the legacy application. Can be null during preactivation phase (selling or change offer that requires new account service) but must be set before final activation. Always set for change/close processes on pre-existing account service.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("externalKey")
  public @Nullable String getExternalKey() {
    return externalKey;
  }

  public void setExternalKey(@Nullable String externalKey) {
    this.externalKey = externalKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData legacyId(@Nullable String legacyId) {
    this.legacyId = legacyId;
    return this;
  }

  /**
   * Internal legacy ID. Set if the legacy application has an internal immutable ID for each account service.
   * @return legacyId
   */
  
  @Schema(name = "legacyId", description = "Internal legacy ID. Set if the legacy application has an internal immutable ID for each account service.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("legacyId")
  public @Nullable String getLegacyId() {
    return legacyId;
  }

  public void setLegacyId(@Nullable String legacyId) {
    this.legacyId = legacyId;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData tsStart(@Nullable OffsetDateTime tsStart) {
    this.tsStart = tsStart;
    return this;
  }

  /**
   * The date time in RFC 3339 format.
   * @return tsStart
   */
  @Valid 
  @Schema(name = "tsStart", example = "2024-01-18T15:04:05Z", description = "The date time in RFC 3339 format.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tsStart")
  public @Nullable OffsetDateTime getTsStart() {
    return tsStart;
  }

  public void setTsStart(@Nullable OffsetDateTime tsStart) {
    this.tsStart = tsStart;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData tsEnd(@Nullable OffsetDateTime tsEnd) {
    this.tsEnd = tsEnd;
    return this;
  }

  /**
   * The date time in RFC 3339 format.
   * @return tsEnd
   */
  @Valid 
  @Schema(name = "tsEnd", example = "2024-01-18T15:04:05Z", description = "The date time in RFC 3339 format.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tsEnd")
  public @Nullable OffsetDateTime getTsEnd() {
    return tsEnd;
  }

  public void setTsEnd(@Nullable OffsetDateTime tsEnd) {
    this.tsEnd = tsEnd;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData settlementSrvActn(@Nullable SrvAcntExtKey settlementSrvActn) {
    this.settlementSrvActn = settlementSrvActn;
    return this;
  }

  /**
   * Get settlementSrvActn
   * @return settlementSrvActn
   */
  @Valid 
  @Schema(name = "settlementSrvActn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("settlementSrvActn")
  public @Nullable SrvAcntExtKey getSettlementSrvActn() {
    return settlementSrvActn;
  }

  public void setSettlementSrvActn(@Nullable SrvAcntExtKey settlementSrvActn) {
    this.settlementSrvActn = settlementSrvActn;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData statusInfo(@Nullable StatusInfoRP statusInfo) {
    this.statusInfo = statusInfo;
    return this;
  }

  /**
   * Get statusInfo
   * @return statusInfo
   */
  @Valid 
  @Schema(name = "statusInfo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("statusInfo")
  public @Nullable StatusInfoRP getStatusInfo() {
    return statusInfo;
  }

  public void setStatusInfo(@Nullable StatusInfoRP statusInfo) {
    this.statusInfo = statusInfo;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData ndgInfo(@Nullable NdgInfo ndgInfo) {
    this.ndgInfo = ndgInfo;
    return this;
  }

  /**
   * Get ndgInfo
   * @return ndgInfo
   */
  @Valid 
  @Schema(name = "ndgInfo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ndgInfo")
  public @Nullable NdgInfo getNdgInfo() {
    return ndgInfo;
  }

  public void setNdgInfo(@Nullable NdgInfo ndgInfo) {
    this.ndgInfo = ndgInfo;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData prodInfo(@Nullable ProdInfo prodInfo) {
    this.prodInfo = prodInfo;
    return this;
  }

  /**
   * Get prodInfo
   * @return prodInfo
   */
  @Valid 
  @Schema(name = "prodInfo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prodInfo")
  public @Nullable ProdInfo getProdInfo() {
    return prodInfo;
  }

  public void setProdInfo(@Nullable ProdInfo prodInfo) {
    this.prodInfo = prodInfo;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnData srvActnInfo(@Nullable Object srvActnInfo) {
    this.srvActnInfo = srvActnInfo;
    return this;
  }

  /**
   * Specific information for the settlement account (theoretically optional, but each account is defined in this entity).
   * @return srvActnInfo
   */
  
  @Schema(name = "srvActnInfo", description = "Specific information for the settlement account (theoretically optional, but each account is defined in this entity).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("srvActnInfo")
  public @Nullable Object getSrvActnInfo() {
    return srvActnInfo;
  }

  public void setSrvActnInfo(@Nullable Object srvActnInfo) {
    this.srvActnInfo = srvActnInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SrvActnData srvActnData = (SrvActnData) o;
    return Objects.equals(this.bankCod, srvActnData.bankCod) &&
        Objects.equals(this.officeCod, srvActnData.officeCod) &&
        Objects.equals(this.srvCod, srvActnData.srvCod) &&
        Objects.equals(this.key, srvActnData.key) &&
        Objects.equals(this.externalKey, srvActnData.externalKey) &&
        Objects.equals(this.legacyId, srvActnData.legacyId) &&
        Objects.equals(this.tsStart, srvActnData.tsStart) &&
        Objects.equals(this.tsEnd, srvActnData.tsEnd) &&
        Objects.equals(this.settlementSrvActn, srvActnData.settlementSrvActn) &&
        Objects.equals(this.statusInfo, srvActnData.statusInfo) &&
        Objects.equals(this.ndgInfo, srvActnData.ndgInfo) &&
        Objects.equals(this.prodInfo, srvActnData.prodInfo) &&
        Objects.equals(this.srvActnInfo, srvActnData.srvActnInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankCod, officeCod, srvCod, key, externalKey, legacyId, tsStart, tsEnd, settlementSrvActn, statusInfo, ndgInfo, prodInfo, srvActnInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SrvActnData {\n");
    sb.append("    bankCod: ").append(toIndentedString(bankCod)).append("\n");
    sb.append("    officeCod: ").append(toIndentedString(officeCod)).append("\n");
    sb.append("    srvCod: ").append(toIndentedString(srvCod)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    externalKey: ").append(toIndentedString(externalKey)).append("\n");
    sb.append("    legacyId: ").append(toIndentedString(legacyId)).append("\n");
    sb.append("    tsStart: ").append(toIndentedString(tsStart)).append("\n");
    sb.append("    tsEnd: ").append(toIndentedString(tsEnd)).append("\n");
    sb.append("    settlementSrvActn: ").append(toIndentedString(settlementSrvActn)).append("\n");
    sb.append("    statusInfo: ").append(toIndentedString(statusInfo)).append("\n");
    sb.append("    ndgInfo: ").append(toIndentedString(ndgInfo)).append("\n");
    sb.append("    prodInfo: ").append(toIndentedString(prodInfo)).append("\n");
    sb.append("    srvActnInfo: ").append(toIndentedString(srvActnInfo)).append("\n");
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

