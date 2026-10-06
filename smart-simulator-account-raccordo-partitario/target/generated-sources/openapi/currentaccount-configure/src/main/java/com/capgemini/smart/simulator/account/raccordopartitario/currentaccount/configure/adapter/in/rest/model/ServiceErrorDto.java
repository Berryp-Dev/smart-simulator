package com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
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
 * Contains the details of each error present in service exceptions.
 */

@Schema(name = "ServiceErrorDto", description = "Contains the details of each error present in service exceptions.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:37.471629900+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class ServiceErrorDto {

  private @Nullable String errorCode;

  private @Nullable String errorMessage;

  private @Nullable String technicalDescription;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ServiceErrorDto errorCode(@Nullable String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Error code.
   * @return errorCode
   */
  
  @Schema(name = "errorCode", description = "Error code.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errorCode")
  public @Nullable String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(@Nullable String errorCode) {
    this.errorCode = errorCode;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ServiceErrorDto errorMessage(@Nullable String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * Error description.
   * @return errorMessage
   */
  
  @Schema(name = "errorMessage", description = "Error description.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errorMessage")
  public @Nullable String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(@Nullable String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ServiceErrorDto technicalDescription(@Nullable String technicalDescription) {
    this.technicalDescription = technicalDescription;
    return this;
  }

  /**
   * Technical details.
   * @return technicalDescription
   */
  
  @Schema(name = "technicalDescription", description = "Technical details.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("technicalDescription")
  public @Nullable String getTechnicalDescription() {
    return technicalDescription;
  }

  public void setTechnicalDescription(@Nullable String technicalDescription) {
    this.technicalDescription = technicalDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceErrorDto serviceErrorDto = (ServiceErrorDto) o;
    return Objects.equals(this.errorCode, serviceErrorDto.errorCode) &&
        Objects.equals(this.errorMessage, serviceErrorDto.errorMessage) &&
        Objects.equals(this.technicalDescription, serviceErrorDto.technicalDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, errorMessage, technicalDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceErrorDto {\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    technicalDescription: ").append(toIndentedString(technicalDescription)).append("\n");
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

