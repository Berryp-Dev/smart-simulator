package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.Header;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SetFinalNdgDtoRequestPayload;
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
 * Request for setting the final NDG for the service account.
 */

@Schema(name = "SetFinalNdgDtoRequest", description = "Request for setting the final NDG for the service account.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SetFinalNdgDtoRequest {

  private @Nullable Header header;

  private @Nullable SetFinalNdgDtoRequestPayload payload;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SetFinalNdgDtoRequest header(@Nullable Header header) {
    this.header = header;
    return this;
  }

  /**
   * Get header
   * @return header
   */
  @Valid 
  @Schema(name = "header", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("header")
  public @Nullable Header getHeader() {
    return header;
  }

  public void setHeader(@Nullable Header header) {
    this.header = header;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SetFinalNdgDtoRequest payload(@Nullable SetFinalNdgDtoRequestPayload payload) {
    this.payload = payload;
    return this;
  }

  /**
   * Get payload
   * @return payload
   */
  @Valid 
  @Schema(name = "payload", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("payload")
  public @Nullable SetFinalNdgDtoRequestPayload getPayload() {
    return payload;
  }

  public void setPayload(@Nullable SetFinalNdgDtoRequestPayload payload) {
    this.payload = payload;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetFinalNdgDtoRequest setFinalNdgDtoRequest = (SetFinalNdgDtoRequest) o;
    return Objects.equals(this.header, setFinalNdgDtoRequest.header) &&
        Objects.equals(this.payload, setFinalNdgDtoRequest.payload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, payload);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetFinalNdgDtoRequest {\n");
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    payload: ").append(toIndentedString(payload)).append("\n");
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

