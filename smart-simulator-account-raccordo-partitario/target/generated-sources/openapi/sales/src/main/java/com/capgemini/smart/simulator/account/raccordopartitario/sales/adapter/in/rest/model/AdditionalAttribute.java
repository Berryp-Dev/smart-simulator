package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

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
 * AdditionalAttribute
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class AdditionalAttribute {

  private String key;

  private String value;

  public AdditionalAttribute() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AdditionalAttribute(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public AdditionalAttribute key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Name of the additional attribute.
   * @return key
   */
  @NotNull 
  @Schema(name = "key", description = "Name of the additional attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("key")
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public AdditionalAttribute value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Value of the additional attribute.
   * @return value
   */
  @NotNull 
  @Schema(name = "value", description = "Value of the additional attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdditionalAttribute additionalAttribute = (AdditionalAttribute) o;
    return Objects.equals(this.key, additionalAttribute.key) &&
        Objects.equals(this.value, additionalAttribute.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdditionalAttribute {\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

