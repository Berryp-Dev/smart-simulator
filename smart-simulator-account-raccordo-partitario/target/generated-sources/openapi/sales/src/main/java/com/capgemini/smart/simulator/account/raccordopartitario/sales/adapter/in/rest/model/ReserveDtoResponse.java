package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvAcntExtKey;
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
 * Response for the reserve service account request.
 */

@Schema(name = "ReserveDtoResponse", description = "Response for the reserve service account request.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class ReserveDtoResponse {

  private @Nullable SrvAcntExtKey srvAcntExtKey;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ReserveDtoResponse srvAcntExtKey(@Nullable SrvAcntExtKey srvAcntExtKey) {
    this.srvAcntExtKey = srvAcntExtKey;
    return this;
  }

  /**
   * Get srvAcntExtKey
   * @return srvAcntExtKey
   */
  @Valid 
  @Schema(name = "srvAcntExtKey", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("srvAcntExtKey")
  public @Nullable SrvAcntExtKey getSrvAcntExtKey() {
    return srvAcntExtKey;
  }

  public void setSrvAcntExtKey(@Nullable SrvAcntExtKey srvAcntExtKey) {
    this.srvAcntExtKey = srvAcntExtKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReserveDtoResponse reserveDtoResponse = (ReserveDtoResponse) o;
    return Objects.equals(this.srvAcntExtKey, reserveDtoResponse.srvAcntExtKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(srvAcntExtKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReserveDtoResponse {\n");
    sb.append("    srvAcntExtKey: ").append(toIndentedString(srvAcntExtKey)).append("\n");
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

