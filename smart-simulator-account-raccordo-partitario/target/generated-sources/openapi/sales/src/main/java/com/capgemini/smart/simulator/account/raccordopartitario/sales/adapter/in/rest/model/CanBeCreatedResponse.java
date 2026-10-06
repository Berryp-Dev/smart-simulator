package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.CanBeCreatedResponseResultsInner;
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
 * Response for the create service account request.
 */

@Schema(name = "CanBeCreatedResponse", description = "Response for the create service account request.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class CanBeCreatedResponse {

  @Valid
  private List<@Valid CanBeCreatedResponseResultsInner> results = new ArrayList<>();

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedResponse results(List<@Valid CanBeCreatedResponseResultsInner> results) {
    this.results = results;
    return this;
  }

  public CanBeCreatedResponse addResultsItem(CanBeCreatedResponseResultsInner resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<>();
    }
    this.results.add(resultsItem);
    return this;
  }

  /**
   * List of results for each service account data checked.
   * @return results
   */
  @Valid 
  @Schema(name = "results", description = "List of results for each service account data checked.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("results")
  public List<@Valid CanBeCreatedResponseResultsInner> getResults() {
    return results;
  }

  public void setResults(List<@Valid CanBeCreatedResponseResultsInner> results) {
    this.results = results;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CanBeCreatedResponse canBeCreatedResponse = (CanBeCreatedResponse) o;
    return Objects.equals(this.results, canBeCreatedResponse.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CanBeCreatedResponse {\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
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

