package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.ServiceErrorDto;
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
 * Object used to return exceptions raised by the microservice&#39;s exposed services.
 */

@Schema(name = "ErrorResponse", description = "Object used to return exceptions raised by the microservice's exposed services.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class ErrorResponse {

  private @Nullable ServiceErrorDto mainError;

  @Valid
  private List<@Valid ServiceErrorDto> errorList = new ArrayList<>();

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ErrorResponse mainError(@Nullable ServiceErrorDto mainError) {
    this.mainError = mainError;
    return this;
  }

  /**
   * Get mainError
   * @return mainError
   */
  @Valid 
  @Schema(name = "mainError", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mainError")
  public @Nullable ServiceErrorDto getMainError() {
    return mainError;
  }

  public void setMainError(@Nullable ServiceErrorDto mainError) {
    this.mainError = mainError;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ErrorResponse errorList(List<@Valid ServiceErrorDto> errorList) {
    this.errorList = errorList;
    return this;
  }

  public ErrorResponse addErrorListItem(ServiceErrorDto errorListItem) {
    if (this.errorList == null) {
      this.errorList = new ArrayList<>();
    }
    this.errorList.add(errorListItem);
    return this;
  }

  /**
   * Additional list of errors (optional).
   * @return errorList
   */
  @Valid 
  @Schema(name = "errorList", description = "Additional list of errors (optional).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errorList")
  public List<@Valid ServiceErrorDto> getErrorList() {
    return errorList;
  }

  public void setErrorList(List<@Valid ServiceErrorDto> errorList) {
    this.errorList = errorList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.mainError, errorResponse.mainError) &&
        Objects.equals(this.errorList, errorResponse.errorList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mainError, errorList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");
    sb.append("    mainError: ").append(toIndentedString(mainError)).append("\n");
    sb.append("    errorList: ").append(toIndentedString(errorList)).append("\n");
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

