package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProdInfoProdAttrsInner
 */

@JsonTypeName("ProdInfo_prodAttrs_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class ProdInfoProdAttrsInner {

  private @Nullable String attrCod;

  private @Nullable String attrVal;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfoProdAttrsInner attrCod(@Nullable String attrCod) {
    this.attrCod = attrCod;
    return this;
  }

  /**
   * Code of the product attribute.
   * @return attrCod
   */
  
  @Schema(name = "attrCod", description = "Code of the product attribute.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attrCod")
  public @Nullable String getAttrCod() {
    return attrCod;
  }

  public void setAttrCod(@Nullable String attrCod) {
    this.attrCod = attrCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfoProdAttrsInner attrVal(@Nullable String attrVal) {
    this.attrVal = attrVal;
    return this;
  }

  /**
   * Value of the product attribute.
   * @return attrVal
   */
  
  @Schema(name = "attrVal", description = "Value of the product attribute.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attrVal")
  public @Nullable String getAttrVal() {
    return attrVal;
  }

  public void setAttrVal(@Nullable String attrVal) {
    this.attrVal = attrVal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProdInfoProdAttrsInner prodInfoProdAttrsInner = (ProdInfoProdAttrsInner) o;
    return Objects.equals(this.attrCod, prodInfoProdAttrsInner.attrCod) &&
        Objects.equals(this.attrVal, prodInfoProdAttrsInner.attrVal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attrCod, attrVal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProdInfoProdAttrsInner {\n");
    sb.append("    attrCod: ").append(toIndentedString(attrCod)).append("\n");
    sb.append("    attrVal: ").append(toIndentedString(attrVal)).append("\n");
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

