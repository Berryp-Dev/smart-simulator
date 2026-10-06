package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * SimpleProductNdgInfo
 */

@JsonTypeName("SimpleProduct_ndgInfo")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-06T23:59:32.741841700+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SimpleProductNdgInfo {

  private String cntrNdgKey;

  private @Nullable String cntrNdgType;

  @Valid
  private List<String> cntrSignNdgKeys = new ArrayList<>();

  /**
   * Nature of the NDG.
   */
  public enum NdgNatureEnum {
    NATURAL_PERSON("NATURAL_PERSON"),
    
    JURIDICAL_PERSON("JURIDICAL_PERSON"),
    
    JOINT_OWNERSHIP("JOINT_OWNERSHIP");

    private final String value;

    NdgNatureEnum(String value) {
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
    public static NdgNatureEnum fromValue(String value) {
      for (NdgNatureEnum b : NdgNatureEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private @Nullable NdgNatureEnum ndgNature;

  public SimpleProductNdgInfo() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SimpleProductNdgInfo(String cntrNdgKey) {
    this.cntrNdgKey = cntrNdgKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProductNdgInfo cntrNdgKey(String cntrNdgKey) {
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
  public SimpleProductNdgInfo cntrNdgType(@Nullable String cntrNdgType) {
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
  public SimpleProductNdgInfo cntrSignNdgKeys(List<String> cntrSignNdgKeys) {
    this.cntrSignNdgKeys = cntrSignNdgKeys;
    return this;
  }

  public SimpleProductNdgInfo addCntrSignNdgKeysItem(String cntrSignNdgKeysItem) {
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

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProductNdgInfo ndgNature(@Nullable NdgNatureEnum ndgNature) {
    this.ndgNature = ndgNature;
    return this;
  }

  /**
   * Nature of the NDG.
   * @return ndgNature
   */
  
  @Schema(name = "ndgNature", example = "NATURAL_PERSON", description = "Nature of the NDG.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ndgNature")
  public @Nullable NdgNatureEnum getNdgNature() {
    return ndgNature;
  }

  public void setNdgNature(@Nullable NdgNatureEnum ndgNature) {
    this.ndgNature = ndgNature;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleProductNdgInfo simpleProductNdgInfo = (SimpleProductNdgInfo) o;
    return Objects.equals(this.cntrNdgKey, simpleProductNdgInfo.cntrNdgKey) &&
        Objects.equals(this.cntrNdgType, simpleProductNdgInfo.cntrNdgType) &&
        Objects.equals(this.cntrSignNdgKeys, simpleProductNdgInfo.cntrSignNdgKeys) &&
        Objects.equals(this.ndgNature, simpleProductNdgInfo.ndgNature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cntrNdgKey, cntrNdgType, cntrSignNdgKeys, ndgNature);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimpleProductNdgInfo {\n");
    sb.append("    cntrNdgKey: ").append(toIndentedString(cntrNdgKey)).append("\n");
    sb.append("    cntrNdgType: ").append(toIndentedString(cntrNdgType)).append("\n");
    sb.append("    cntrSignNdgKeys: ").append(toIndentedString(cntrSignNdgKeys)).append("\n");
    sb.append("    ndgNature: ").append(toIndentedString(ndgNature)).append("\n");
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

