package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
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
 * NdgInfo
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class NdgInfo {

  private String cntrNdgKey;

  private @Nullable String cntrNdgType;

  @Valid
  private List<String> cntrSignNdgKeys = new ArrayList<>();

  public NdgInfo() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public NdgInfo(String cntrNdgKey) {
    this.cntrNdgKey = cntrNdgKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public NdgInfo cntrNdgKey(String cntrNdgKey) {
    this.cntrNdgKey = cntrNdgKey;
    return this;
  }

  /**
   * NDG identifier of contract owner.
   * @return cntrNdgKey
   */
  @NotNull 
  @Schema(name = "cntrNdgKey", description = "NDG identifier of contract owner.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cntrNdgKey")
  public String getCntrNdgKey() {
    return cntrNdgKey;
  }

  public void setCntrNdgKey(String cntrNdgKey) {
    this.cntrNdgKey = cntrNdgKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public NdgInfo cntrNdgType(@Nullable String cntrNdgType) {
    this.cntrNdgType = cntrNdgType;
    return this;
  }

  /**
   * Type of NDG (e.g., Prospect, Customer, etc.).
   * @return cntrNdgType
   */
  
  @Schema(name = "cntrNdgType", description = "Type of NDG (e.g., Prospect, Customer, etc.).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cntrNdgType")
  public @Nullable String getCntrNdgType() {
    return cntrNdgType;
  }

  public void setCntrNdgType(@Nullable String cntrNdgType) {
    this.cntrNdgType = cntrNdgType;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public NdgInfo cntrSignNdgKeys(List<String> cntrSignNdgKeys) {
    this.cntrSignNdgKeys = cntrSignNdgKeys;
    return this;
  }

  public NdgInfo addCntrSignNdgKeysItem(String cntrSignNdgKeysItem) {
    if (this.cntrSignNdgKeys == null) {
      this.cntrSignNdgKeys = new ArrayList<>();
    }
    this.cntrSignNdgKeys.add(cntrSignNdgKeysItem);
    return this;
  }

  /**
   * List of NDG identifiers for contract signatories.
   * @return cntrSignNdgKeys
   */
  
  @Schema(name = "cntrSignNdgKeys", description = "List of NDG identifiers for contract signatories.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cntrSignNdgKeys")
  public List<String> getCntrSignNdgKeys() {
    return cntrSignNdgKeys;
  }

  public void setCntrSignNdgKeys(List<String> cntrSignNdgKeys) {
    this.cntrSignNdgKeys = cntrSignNdgKeys;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NdgInfo ndgInfo = (NdgInfo) o;
    return Objects.equals(this.cntrNdgKey, ndgInfo.cntrNdgKey) &&
        Objects.equals(this.cntrNdgType, ndgInfo.cntrNdgType) &&
        Objects.equals(this.cntrSignNdgKeys, ndgInfo.cntrSignNdgKeys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cntrNdgKey, cntrNdgType, cntrSignNdgKeys);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NdgInfo {\n");
    sb.append("    cntrNdgKey: ").append(toIndentedString(cntrNdgKey)).append("\n");
    sb.append("    cntrNdgType: ").append(toIndentedString(cntrNdgType)).append("\n");
    sb.append("    cntrSignNdgKeys: ").append(toIndentedString(cntrSignNdgKeys)).append("\n");
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

