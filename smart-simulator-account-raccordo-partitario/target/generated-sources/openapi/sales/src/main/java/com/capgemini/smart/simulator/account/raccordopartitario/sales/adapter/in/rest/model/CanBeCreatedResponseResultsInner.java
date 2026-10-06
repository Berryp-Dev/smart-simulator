package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.AdditionalAttribute;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SimpleProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * CanBeCreatedResponseResultsInner
 */

@JsonTypeName("CanBeCreatedResponse_results_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class CanBeCreatedResponseResultsInner {

  private SimpleProduct srvActnPreData;

  private Boolean canBeCreated;

  private @Nullable String motivationCode;

  private @Nullable String motivationDescription;

  @Valid
  private List<@Valid AdditionalAttribute> additionalInfo = new ArrayList<>();

  public CanBeCreatedResponseResultsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CanBeCreatedResponseResultsInner(SimpleProduct srvActnPreData, Boolean canBeCreated) {
    this.srvActnPreData = srvActnPreData;
    this.canBeCreated = canBeCreated;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedResponseResultsInner srvActnPreData(SimpleProduct srvActnPreData) {
    this.srvActnPreData = srvActnPreData;
    return this;
  }

  /**
   * Get srvActnPreData
   * @return srvActnPreData
   */
  @NotNull @Valid 
  @Schema(name = "srvActnPreData", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("srvActnPreData")
  public SimpleProduct getSrvActnPreData() {
    return srvActnPreData;
  }

  public void setSrvActnPreData(SimpleProduct srvActnPreData) {
    this.srvActnPreData = srvActnPreData;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedResponseResultsInner canBeCreated(Boolean canBeCreated) {
    this.canBeCreated = canBeCreated;
    return this;
  }

  /**
   * Indicates if the service account can be created.
   * @return canBeCreated
   */
  @NotNull 
  @Schema(name = "canBeCreated", description = "Indicates if the service account can be created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("canBeCreated")
  public Boolean getCanBeCreated() {
    return canBeCreated;
  }

  public void setCanBeCreated(Boolean canBeCreated) {
    this.canBeCreated = canBeCreated;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedResponseResultsInner motivationCode(@Nullable String motivationCode) {
    this.motivationCode = motivationCode;
    return this;
  }

  /**
   * Motivation code for the result.
   * @return motivationCode
   */
  
  @Schema(name = "motivationCode", example = "NC_001", description = "Motivation code for the result.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("motivationCode")
  public @Nullable String getMotivationCode() {
    return motivationCode;
  }

  public void setMotivationCode(@Nullable String motivationCode) {
    this.motivationCode = motivationCode;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedResponseResultsInner motivationDescription(@Nullable String motivationDescription) {
    this.motivationDescription = motivationDescription;
    return this;
  }

  /**
   * Motivation description for the result.
   * @return motivationDescription
   */
  
  @Schema(name = "motivationDescription", example = "The service account cannot be created due to missing information.", description = "Motivation description for the result.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("motivationDescription")
  public @Nullable String getMotivationDescription() {
    return motivationDescription;
  }

  public void setMotivationDescription(@Nullable String motivationDescription) {
    this.motivationDescription = motivationDescription;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedResponseResultsInner additionalInfo(List<@Valid AdditionalAttribute> additionalInfo) {
    this.additionalInfo = additionalInfo;
    return this;
  }

  public CanBeCreatedResponseResultsInner addAdditionalInfoItem(AdditionalAttribute additionalInfoItem) {
    if (this.additionalInfo == null) {
      this.additionalInfo = new ArrayList<>();
    }
    this.additionalInfo.add(additionalInfoItem);
    return this;
  }

  /**
   * List of additional information should be given.
   * @return additionalInfo
   */
  @Valid 
  @Schema(name = "additionalInfo", description = "List of additional information should be given.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalInfo")
  public List<@Valid AdditionalAttribute> getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(List<@Valid AdditionalAttribute> additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CanBeCreatedResponseResultsInner canBeCreatedResponseResultsInner = (CanBeCreatedResponseResultsInner) o;
    return Objects.equals(this.srvActnPreData, canBeCreatedResponseResultsInner.srvActnPreData) &&
        Objects.equals(this.canBeCreated, canBeCreatedResponseResultsInner.canBeCreated) &&
        Objects.equals(this.motivationCode, canBeCreatedResponseResultsInner.motivationCode) &&
        Objects.equals(this.motivationDescription, canBeCreatedResponseResultsInner.motivationDescription) &&
        Objects.equals(this.additionalInfo, canBeCreatedResponseResultsInner.additionalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(srvActnPreData, canBeCreated, motivationCode, motivationDescription, additionalInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CanBeCreatedResponseResultsInner {\n");
    sb.append("    srvActnPreData: ").append(toIndentedString(srvActnPreData)).append("\n");
    sb.append("    canBeCreated: ").append(toIndentedString(canBeCreated)).append("\n");
    sb.append("    motivationCode: ").append(toIndentedString(motivationCode)).append("\n");
    sb.append("    motivationDescription: ").append(toIndentedString(motivationDescription)).append("\n");
    sb.append("    additionalInfo: ").append(toIndentedString(additionalInfo)).append("\n");
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

