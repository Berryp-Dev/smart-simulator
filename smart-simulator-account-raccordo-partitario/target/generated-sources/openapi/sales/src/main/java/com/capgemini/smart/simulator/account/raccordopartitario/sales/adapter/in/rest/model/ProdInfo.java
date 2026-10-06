package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.ProdInfoAgrmntsInner;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.ProdInfoProdAttrsInner;
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
 * ProdInfo
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class ProdInfo {

  private @Nullable String prodKey;

  @Valid
  private List<String> moduleKeys = new ArrayList<>();

  @Valid
  private List<@Valid ProdInfoAgrmntsInner> agrmnts = new ArrayList<>();

  @Valid
  private List<String> prodExtKeys = new ArrayList<>();

  @Valid
  private List<@Valid ProdInfoProdAttrsInner> prodAttrs = new ArrayList<>();

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfo prodKey(@Nullable String prodKey) {
    this.prodKey = prodKey;
    return this;
  }

  /**
   * Product code.
   * @return prodKey
   */
  
  @Schema(name = "prodKey", description = "Product code.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prodKey")
  public @Nullable String getProdKey() {
    return prodKey;
  }

  public void setProdKey(@Nullable String prodKey) {
    this.prodKey = prodKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfo moduleKeys(List<String> moduleKeys) {
    this.moduleKeys = moduleKeys;
    return this;
  }

  public ProdInfo addModuleKeysItem(String moduleKeysItem) {
    if (this.moduleKeys == null) {
      this.moduleKeys = new ArrayList<>();
    }
    this.moduleKeys.add(moduleKeysItem);
    return this;
  }

  /**
   * Module codes associated with the product (either active or to be activated).
   * @return moduleKeys
   */
  
  @Schema(name = "moduleKeys", description = "Module codes associated with the product (either active or to be activated).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("moduleKeys")
  public List<String> getModuleKeys() {
    return moduleKeys;
  }

  public void setModuleKeys(List<String> moduleKeys) {
    this.moduleKeys = moduleKeys;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfo agrmnts(List<@Valid ProdInfoAgrmntsInner> agrmnts) {
    this.agrmnts = agrmnts;
    return this;
  }

  public ProdInfo addAgrmntsItem(ProdInfoAgrmntsInner agrmntsItem) {
    if (this.agrmnts == null) {
      this.agrmnts = new ArrayList<>();
    }
    this.agrmnts.add(agrmntsItem);
    return this;
  }

  /**
   * List of modules (optional for single-module cases).
   * @return agrmnts
   */
  @Valid 
  @Schema(name = "agrmnts", description = "List of modules (optional for single-module cases).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("agrmnts")
  public List<@Valid ProdInfoAgrmntsInner> getAgrmnts() {
    return agrmnts;
  }

  public void setAgrmnts(List<@Valid ProdInfoAgrmntsInner> agrmnts) {
    this.agrmnts = agrmnts;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfo prodExtKeys(List<String> prodExtKeys) {
    this.prodExtKeys = prodExtKeys;
    return this;
  }

  public ProdInfo addProdExtKeysItem(String prodExtKeysItem) {
    if (this.prodExtKeys == null) {
      this.prodExtKeys = new ArrayList<>();
    }
    this.prodExtKeys.add(prodExtKeysItem);
    return this;
  }

  /**
   * List of external product keys.
   * @return prodExtKeys
   */
  
  @Schema(name = "prodExtKeys", description = "List of external product keys.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prodExtKeys")
  public List<String> getProdExtKeys() {
    return prodExtKeys;
  }

  public void setProdExtKeys(List<String> prodExtKeys) {
    this.prodExtKeys = prodExtKeys;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfo prodAttrs(List<@Valid ProdInfoProdAttrsInner> prodAttrs) {
    this.prodAttrs = prodAttrs;
    return this;
  }

  public ProdInfo addProdAttrsItem(ProdInfoProdAttrsInner prodAttrsItem) {
    if (this.prodAttrs == null) {
      this.prodAttrs = new ArrayList<>();
    }
    this.prodAttrs.add(prodAttrsItem);
    return this;
  }

  /**
   * List of product attributes.
   * @return prodAttrs
   */
  @Valid 
  @Schema(name = "prodAttrs", description = "List of product attributes.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prodAttrs")
  public List<@Valid ProdInfoProdAttrsInner> getProdAttrs() {
    return prodAttrs;
  }

  public void setProdAttrs(List<@Valid ProdInfoProdAttrsInner> prodAttrs) {
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
    ProdInfo prodInfo = (ProdInfo) o;
    return Objects.equals(this.prodKey, prodInfo.prodKey) &&
        Objects.equals(this.moduleKeys, prodInfo.moduleKeys) &&
        Objects.equals(this.agrmnts, prodInfo.agrmnts) &&
        Objects.equals(this.prodExtKeys, prodInfo.prodExtKeys) &&
        Objects.equals(this.prodAttrs, prodInfo.prodAttrs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prodKey, moduleKeys, agrmnts, prodExtKeys, prodAttrs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProdInfo {\n");
    sb.append("    prodKey: ").append(toIndentedString(prodKey)).append("\n");
    sb.append("    moduleKeys: ").append(toIndentedString(moduleKeys)).append("\n");
    sb.append("    agrmnts: ").append(toIndentedString(agrmnts)).append("\n");
    sb.append("    prodExtKeys: ").append(toIndentedString(prodExtKeys)).append("\n");
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

