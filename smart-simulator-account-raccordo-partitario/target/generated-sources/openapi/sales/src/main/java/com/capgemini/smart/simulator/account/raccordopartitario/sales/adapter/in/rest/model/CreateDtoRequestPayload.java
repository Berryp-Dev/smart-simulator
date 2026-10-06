package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvActnData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateDtoRequestPayload
 */

@JsonTypeName("CreateDtoRequest_payload")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class CreateDtoRequestPayload {

  private @Nullable String caseID;

  private @Nullable SrvActnData srvActnData;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CreateDtoRequestPayload caseID(@Nullable String caseID) {
    this.caseID = caseID;
    return this;
  }

  /**
   * ID of the Case.
   * @return caseID
   */
  
  @Schema(name = "caseID", example = "123e4567-e89b-12d3-a456-426614174000", description = "ID of the Case.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("caseID")
  public @Nullable String getCaseID() {
    return caseID;
  }

  public void setCaseID(@Nullable String caseID) {
    this.caseID = caseID;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CreateDtoRequestPayload srvActnData(@Nullable SrvActnData srvActnData) {
    this.srvActnData = srvActnData;
    return this;
  }

  /**
   * Get srvActnData
   * @return srvActnData
   */
  @Valid 
  @Schema(name = "srvActnData", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("srvActnData")
  public @Nullable SrvActnData getSrvActnData() {
    return srvActnData;
  }

  public void setSrvActnData(@Nullable SrvActnData srvActnData) {
    this.srvActnData = srvActnData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateDtoRequestPayload createDtoRequestPayload = (CreateDtoRequestPayload) o;
    return Objects.equals(this.caseID, createDtoRequestPayload.caseID) &&
        Objects.equals(this.srvActnData, createDtoRequestPayload.srvActnData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(caseID, srvActnData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateDtoRequestPayload {\n");
    sb.append("    caseID: ").append(toIndentedString(caseID)).append("\n");
    sb.append("    srvActnData: ").append(toIndentedString(srvActnData)).append("\n");
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

