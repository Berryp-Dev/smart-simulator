package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SimpleProductExternalProdInfoProdAttrsInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * External product information for the service account.
 */

@Schema(name = "SimpleProduct_externalProdInfo", description = "External product information for the service account.")
@JsonTypeName("SimpleProduct_externalProdInfo")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SimpleProductExternalProdInfo {

  private String prodExtKey;

  @Valid
  private List<@Valid SimpleProductExternalProdInfoProdAttrsInner> prodAttrs = new ArrayList<>();

  public SimpleProductExternalProdInfo() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SimpleProductExternalProdInfo(String prodExtKey) {
    this.prodExtKey = prodExtKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProductExternalProdInfo prodExtKey(String prodExtKey) {
    this.prodExtKey = prodExtKey;
    return this;
  }

  /**
   * External product key.
   * @return prodExtKey
   */
  @NotNull 
  @Schema(name = "prodExtKey", example = "D001", description = "External product key.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prodExtKey")
  public String getProdExtKey() {
    return prodExtKey;
  }

  public void setProdExtKey(String prodExtKey) {
    this.prodExtKey = prodExtKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProductExternalProdInfo prodAttrs(List<@Valid SimpleProductExternalProdInfoProdAttrsInner> prodAttrs) {
    this.prodAttrs = prodAttrs;
    return this;
  }

  public SimpleProductExternalProdInfo addProdAttrsItem(SimpleProductExternalProdInfoProdAttrsInner prodAttrsItem) {
    if (this.prodAttrs == null) {
      this.prodAttrs = new ArrayList<>();
    }
    this.prodAttrs.add(prodAttrsItem);
    return this;
  }

  /**
   * List of product attributes from CPQ catalog.
   * @return prodAttrs
   */
  @Valid 
  @Schema(name = "prodAttrs", description = "List of product attributes from CPQ catalog.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prodAttrs")
  public List<@Valid SimpleProductExternalProdInfoProdAttrsInner> getProdAttrs() {
    return prodAttrs;
  }

  public void setProdAttrs(List<@Valid SimpleProductExternalProdInfoProdAttrsInner> prodAttrs) {
    this.prodAttrs = prodAttrs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleProductExternalProdInfo simpleProductExternalProdInfo = (SimpleProductExternalProdInfo) o;
    return Objects.equals(this.prodExtKey, simpleProductExternalProdInfo.prodExtKey) &&
        Objects.equals(this.prodAttrs, simpleProductExternalProdInfo.prodAttrs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prodExtKey, prodAttrs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimpleProductExternalProdInfo {\n");
    sb.append("    prodExtKey: ").append(toIndentedString(prodExtKey)).append("\n");
    sb.append("    prodAttrs: ").append(toIndentedString(prodAttrs)).append("\n");
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

