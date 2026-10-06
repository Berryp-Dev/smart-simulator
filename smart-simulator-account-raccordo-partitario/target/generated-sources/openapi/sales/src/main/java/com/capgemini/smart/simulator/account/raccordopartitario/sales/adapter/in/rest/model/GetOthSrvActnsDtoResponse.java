package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvActnData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Response for retrieving associated account services.
 */

@Schema(name = "GetOthSrvActnsDtoResponse", description = "Response for retrieving associated account services.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class GetOthSrvActnsDtoResponse {

  @Valid
  private List<@Valid SrvActnData> srvActnData = new ArrayList<>();

  @com.fasterxml.jackson.annotation.JsonIgnore
  public GetOthSrvActnsDtoResponse srvActnData(List<@Valid SrvActnData> srvActnData) {
    this.srvActnData = srvActnData;
    return this;
  }

  public GetOthSrvActnsDtoResponse addSrvActnDataItem(SrvActnData srvActnDataItem) {
    if (this.srvActnData == null) {
      this.srvActnData = new ArrayList<>();
    }
    this.srvActnData.add(srvActnDataItem);
    return this;
  }

  /**
   * Get srvActnData
   * @return srvActnData
   */
  @Valid 
  @Schema(name = "srvActnData", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("srvActnData")
  public List<@Valid SrvActnData> getSrvActnData() {
    return srvActnData;
  }

  public void setSrvActnData(List<@Valid SrvActnData> srvActnData) {
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
    GetOthSrvActnsDtoResponse getOthSrvActnsDtoResponse = (GetOthSrvActnsDtoResponse) o;
    return Objects.equals(this.srvActnData, getOthSrvActnsDtoResponse.srvActnData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(srvActnData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetOthSrvActnsDtoResponse {\n");
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

