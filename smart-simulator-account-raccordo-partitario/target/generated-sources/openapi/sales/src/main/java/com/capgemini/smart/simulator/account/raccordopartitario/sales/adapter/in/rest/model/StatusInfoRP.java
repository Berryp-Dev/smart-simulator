package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Status information.
 */

@Schema(name = "StatusInfoRP", description = "Status information.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class StatusInfoRP {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime tsStatus;

  /**
   * Status code.
   */
  public enum StatusEnum {
    CREATED("CREATED"),
    
    CONFIGURED("CONFIGURED"),
    
    RESERVED("RESERVED"),
    
    ACTIVE("ACTIVE"),
    
    CANCELED("CANCELED");

    private final String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private @Nullable StatusEnum status;

  private @Nullable String sStatus;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public StatusInfoRP tsStatus(@Nullable OffsetDateTime tsStatus) {
    this.tsStatus = tsStatus;
    return this;
  }

  /**
   * The date time in RFC 3339 format.
   * @return tsStatus
   */
  @Valid 
  @Schema(name = "tsStatus", example = "2024-01-18T15:04:05Z", description = "The date time in RFC 3339 format.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tsStatus")
  public @Nullable OffsetDateTime getTsStatus() {
    return tsStatus;
  }

  public void setTsStatus(@Nullable OffsetDateTime tsStatus) {
    this.tsStatus = tsStatus;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public StatusInfoRP status(@Nullable StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Status code.
   * @return status
   */
  
  @Schema(name = "status", description = "Status code.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public @Nullable StatusEnum getStatus() {
    return status;
  }

  public void setStatus(@Nullable StatusEnum status) {
    this.status = status;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public StatusInfoRP sStatus(@Nullable String sStatus) {
    this.sStatus = sStatus;
    return this;
  }

  /**
   * Sub status code.
   * @return sStatus
   */
  
  @Schema(name = "sStatus", description = "Sub status code.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sStatus")
  public @Nullable String getsStatus() {
    return sStatus;
  }

  public void setsStatus(@Nullable String sStatus) {
    this.sStatus = sStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatusInfoRP statusInfoRP = (StatusInfoRP) o;
    return Objects.equals(this.tsStatus, statusInfoRP.tsStatus) &&
        Objects.equals(this.status, statusInfoRP.status) &&
        Objects.equals(this.sStatus, statusInfoRP.sStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tsStatus, status, sStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatusInfoRP {\n");
    sb.append("    tsStatus: ").append(toIndentedString(tsStatus)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    sStatus: ").append(toIndentedString(sStatus)).append("\n");
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

