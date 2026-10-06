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
 * AdditionalAttributes
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class AdditionalAttributes {

  @Valid
  private List<@Valid AdditionalAttribute> qualifiers = new ArrayList<>();

  @Valid
  private List<@Valid AdditionalAttribute> ancillaryData = new ArrayList<>();

  @com.fasterxml.jackson.annotation.JsonIgnore
  public AdditionalAttributes qualifiers(List<@Valid AdditionalAttribute> qualifiers) {
    this.qualifiers = qualifiers;
    return this;
  }

  public AdditionalAttributes addQualifiersItem(AdditionalAttribute qualifiersItem) {
    if (this.qualifiers == null) {
      this.qualifiers = new ArrayList<>();
    }
    this.qualifiers.add(qualifiersItem);
    return this;
  }

  /**
   * Get qualifiers
   * @return qualifiers
   */
  @Valid 
  @Schema(name = "qualifiers", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("qualifiers")
  public List<@Valid AdditionalAttribute> getQualifiers() {
    return qualifiers;
  }

  public void setQualifiers(List<@Valid AdditionalAttribute> qualifiers) {
    this.qualifiers = qualifiers;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public AdditionalAttributes ancillaryData(List<@Valid AdditionalAttribute> ancillaryData) {
    this.ancillaryData = ancillaryData;
    return this;
  }

  public AdditionalAttributes addAncillaryDataItem(AdditionalAttribute ancillaryDataItem) {
    if (this.ancillaryData == null) {
      this.ancillaryData = new ArrayList<>();
    }
    this.ancillaryData.add(ancillaryDataItem);
    return this;
  }

  /**
   * Get ancillaryData
   * @return ancillaryData
   */
  @Valid 
  @Schema(name = "ancillaryData", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ancillaryData")
  public List<@Valid AdditionalAttribute> getAncillaryData() {
    return ancillaryData;
  }

  public void setAncillaryData(List<@Valid AdditionalAttribute> ancillaryData) {
    this.ancillaryData = ancillaryData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdditionalAttributes additionalAttributes = (AdditionalAttributes) o;
    return Objects.equals(this.qualifiers, additionalAttributes.qualifiers) &&
        Objects.equals(this.ancillaryData, additionalAttributes.ancillaryData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(qualifiers, ancillaryData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdditionalAttributes {\n");
    sb.append("    qualifiers: ").append(toIndentedString(qualifiers)).append("\n");
    sb.append("    ancillaryData: ").append(toIndentedString(ancillaryData)).append("\n");
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

