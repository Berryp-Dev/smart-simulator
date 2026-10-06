package com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model.CurrentAccountsSrvActnInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model.SrvAcntKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CurrentAccountsConfigureRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:54.504437400+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class CurrentAccountsConfigureRequest {

  private SrvAcntKey srvAcntKey;

  private CurrentAccountsSrvActnInfo srvActnInfo;

  public CurrentAccountsConfigureRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CurrentAccountsConfigureRequest(SrvAcntKey srvAcntKey, CurrentAccountsSrvActnInfo srvActnInfo) {
    this.srvAcntKey = srvAcntKey;
    this.srvActnInfo = srvActnInfo;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CurrentAccountsConfigureRequest srvAcntKey(SrvAcntKey srvAcntKey) {
    this.srvAcntKey = srvAcntKey;
    return this;
  }

  /**
   * Get srvAcntKey
   * @return srvAcntKey
   */
  @NotNull @Valid 
  @Schema(name = "srvAcntKey", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("srvAcntKey")
  public SrvAcntKey getSrvAcntKey() {
    return srvAcntKey;
  }

  public void setSrvAcntKey(SrvAcntKey srvAcntKey) {
    this.srvAcntKey = srvAcntKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CurrentAccountsConfigureRequest srvActnInfo(CurrentAccountsSrvActnInfo srvActnInfo) {
    this.srvActnInfo = srvActnInfo;
    return this;
  }

  /**
   * Get srvActnInfo
   * @return srvActnInfo
   */
  @NotNull @Valid 
  @Schema(name = "srvActnInfo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("srvActnInfo")
  public CurrentAccountsSrvActnInfo getSrvActnInfo() {
    return srvActnInfo;
  }

  public void setSrvActnInfo(CurrentAccountsSrvActnInfo srvActnInfo) {
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
    CurrentAccountsConfigureRequest currentAccountsConfigureRequest = (CurrentAccountsConfigureRequest) o;
    return Objects.equals(this.srvAcntKey, currentAccountsConfigureRequest.srvAcntKey) &&
        Objects.equals(this.srvActnInfo, currentAccountsConfigureRequest.srvActnInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(srvAcntKey, srvActnInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrentAccountsConfigureRequest {\n");
    sb.append("    srvAcntKey: ").append(toIndentedString(srvAcntKey)).append("\n");
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

