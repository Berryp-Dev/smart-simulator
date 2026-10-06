package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.AdditionalAttribute;
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
 * Response for activating a service account.
 */

@Schema(name = "ActivateDtoResponse", description = "Response for activating a service account.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class ActivateDtoResponse {

  @Valid
  private List<@Valid AdditionalAttribute> newAncillaryData = new ArrayList<>();

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ActivateDtoResponse newAncillaryData(List<@Valid AdditionalAttribute> newAncillaryData) {
    this.newAncillaryData = newAncillaryData;
    return this;
  }

  public ActivateDtoResponse addNewAncillaryDataItem(AdditionalAttribute newAncillaryDataItem) {
    if (this.newAncillaryData == null) {
      this.newAncillaryData = new ArrayList<>();
    }
    this.newAncillaryData.add(newAncillaryDataItem);
    return this;
  }

  /**
   * List of new ancillary data created during activation.
   * @return newAncillaryData
   */
  @Valid 
  @Schema(name = "newAncillaryData", description = "List of new ancillary data created during activation.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("newAncillaryData")
  public List<@Valid AdditionalAttribute> getNewAncillaryData() {
    return newAncillaryData;
  }

  public void setNewAncillaryData(List<@Valid AdditionalAttribute> newAncillaryData) {
    this.newAncillaryData = newAncillaryData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivateDtoResponse activateDtoResponse = (ActivateDtoResponse) o;
    return Objects.equals(this.newAncillaryData, activateDtoResponse.newAncillaryData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newAncillaryData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActivateDtoResponse {\n");
    sb.append("    newAncillaryData: ").append(toIndentedString(newAncillaryData)).append("\n");
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

