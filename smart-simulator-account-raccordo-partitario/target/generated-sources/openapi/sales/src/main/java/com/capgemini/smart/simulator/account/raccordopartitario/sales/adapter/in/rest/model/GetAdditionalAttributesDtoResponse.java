package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.AdditionalAttributes;
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
 * Generic list of additional attributes as key-value pairs.
 */

@Schema(name = "GetAdditionalAttributesDtoResponse", description = "Generic list of additional attributes as key-value pairs.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class GetAdditionalAttributesDtoResponse {

  private @Nullable AdditionalAttributes accountAdditionalAttributes;

  private @Nullable AdditionalAttributes contractAdditionalAttributes;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public GetAdditionalAttributesDtoResponse accountAdditionalAttributes(@Nullable AdditionalAttributes accountAdditionalAttributes) {
    this.accountAdditionalAttributes = accountAdditionalAttributes;
    return this;
  }

  /**
   * Get accountAdditionalAttributes
   * @return accountAdditionalAttributes
   */
  @Valid 
  @Schema(name = "accountAdditionalAttributes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountAdditionalAttributes")
  public @Nullable AdditionalAttributes getAccountAdditionalAttributes() {
    return accountAdditionalAttributes;
  }

  public void setAccountAdditionalAttributes(@Nullable AdditionalAttributes accountAdditionalAttributes) {
    this.accountAdditionalAttributes = accountAdditionalAttributes;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public GetAdditionalAttributesDtoResponse contractAdditionalAttributes(@Nullable AdditionalAttributes contractAdditionalAttributes) {
    this.contractAdditionalAttributes = contractAdditionalAttributes;
    return this;
  }

  /**
   * Get contractAdditionalAttributes
   * @return contractAdditionalAttributes
   */
  @Valid 
  @Schema(name = "contractAdditionalAttributes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractAdditionalAttributes")
  public @Nullable AdditionalAttributes getContractAdditionalAttributes() {
    return contractAdditionalAttributes;
  }

  public void setContractAdditionalAttributes(@Nullable AdditionalAttributes contractAdditionalAttributes) {
    this.contractAdditionalAttributes = contractAdditionalAttributes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetAdditionalAttributesDtoResponse getAdditionalAttributesDtoResponse = (GetAdditionalAttributesDtoResponse) o;
    return Objects.equals(this.accountAdditionalAttributes, getAdditionalAttributesDtoResponse.accountAdditionalAttributes) &&
        Objects.equals(this.contractAdditionalAttributes, getAdditionalAttributesDtoResponse.contractAdditionalAttributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountAdditionalAttributes, contractAdditionalAttributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetAdditionalAttributesDtoResponse {\n");
    sb.append("    accountAdditionalAttributes: ").append(toIndentedString(accountAdditionalAttributes)).append("\n");
    sb.append("    contractAdditionalAttributes: ").append(toIndentedString(contractAdditionalAttributes)).append("\n");
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

