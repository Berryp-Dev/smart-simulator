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
 * SimpleProductExternalProdInfoProdAttrsInner
 */

@JsonTypeName("SimpleProduct_externalProdInfo_prodAttrs_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SimpleProductExternalProdInfoProdAttrsInner {

  private String attrCod;

  private String attrVal;

  public SimpleProductExternalProdInfoProdAttrsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SimpleProductExternalProdInfoProdAttrsInner(String attrCod, String attrVal) {
    this.attrCod = attrCod;
    this.attrVal = attrVal;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProductExternalProdInfoProdAttrsInner attrCod(String attrCod) {
    this.attrCod = attrCod;
    return this;
  }

  /**
   * Code of the product attribute CPQ layers have '#' character as joiner -> \"TEMPLATE#GRUPPO#SOTTOGRUPO#ATTRIBUTO#DATO\".
   * @return attrCod
   */
  @NotNull 
  @Schema(name = "attrCod", example = "002#SIMV#ALLOC#33#21", description = "Code of the product attribute CPQ layers have '#' character as joiner -> \"TEMPLATE#GRUPPO#SOTTOGRUPO#ATTRIBUTO#DATO\".", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("attrCod")
  public String getAttrCod() {
    return attrCod;
  }

  public void setAttrCod(String attrCod) {
    this.attrCod = attrCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProductExternalProdInfoProdAttrsInner attrVal(String attrVal) {
    this.attrVal = attrVal;
    return this;
  }

  /**
   * Value of the product attribute.
   * @return attrVal
   */
  @NotNull 
  @Schema(name = "attrVal", example = "Value1", description = "Value of the product attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("attrVal")
  public String getAttrVal() {
    return attrVal;
  }

  public void setAttrVal(String attrVal) {
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
    SimpleProductExternalProdInfoProdAttrsInner simpleProductExternalProdInfoProdAttrsInner = (SimpleProductExternalProdInfoProdAttrsInner) o;
    return Objects.equals(this.attrCod, simpleProductExternalProdInfoProdAttrsInner.attrCod) &&
        Objects.equals(this.attrVal, simpleProductExternalProdInfoProdAttrsInner.attrVal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attrCod, attrVal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimpleProductExternalProdInfoProdAttrsInner {\n");
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

