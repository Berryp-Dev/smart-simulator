package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvAcntKey;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.StatusInfoRP;
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
 * Response for the create service account request.
 */

@Schema(name = "CreateDtoResponse", description = "Response for the create service account request.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class CreateDtoResponse {

  private @Nullable SrvAcntKey srvAcntKey;

  private @Nullable StatusInfoRP statusInfo;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CreateDtoResponse srvAcntKey(@Nullable SrvAcntKey srvAcntKey) {
    this.srvAcntKey = srvAcntKey;
    return this;
  }

  /**
   * Get srvAcntKey
   * @return srvAcntKey
   */
  @Valid 
  @Schema(name = "srvAcntKey", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("srvAcntKey")
  public @Nullable SrvAcntKey getSrvAcntKey() {
    return srvAcntKey;
  }

  public void setSrvAcntKey(@Nullable SrvAcntKey srvAcntKey) {
    this.srvAcntKey = srvAcntKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CreateDtoResponse statusInfo(@Nullable StatusInfoRP statusInfo) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateDtoResponse createDtoResponse = (CreateDtoResponse) o;
    return Objects.equals(this.srvAcntKey, createDtoResponse.srvAcntKey) &&
        Objects.equals(this.statusInfo, createDtoResponse.statusInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(srvAcntKey, statusInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateDtoResponse {\n");
    sb.append("    srvAcntKey: ").append(toIndentedString(srvAcntKey)).append("\n");
    sb.append("    statusInfo: ").append(toIndentedString(statusInfo)).append("\n");
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

