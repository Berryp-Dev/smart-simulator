package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvActnData;
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
 * Response for retrieving main information of the service account.
 */

@Schema(name = "GetMainInfoDtoResponse", description = "Response for retrieving main information of the service account.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class GetMainInfoDtoResponse {

  private @Nullable SrvActnData srvActnData;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public GetMainInfoDtoResponse srvActnData(@Nullable SrvActnData srvActnData) {
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
    GetMainInfoDtoResponse getMainInfoDtoResponse = (GetMainInfoDtoResponse) o;
    return Objects.equals(this.srvActnData, getMainInfoDtoResponse.srvActnData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(srvActnData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetMainInfoDtoResponse {\n");
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

