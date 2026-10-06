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
 * ProdInfoAgrmntsInner
 */

@JsonTypeName("ProdInfo_agrmnts_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class ProdInfoAgrmntsInner {

  private String ty = "RAG";

  private String sTy = "CNV";

  private String key;

  public ProdInfoAgrmntsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProdInfoAgrmntsInner(String ty, String sTy, String key) {
    this.ty = ty;
    this.sTy = sTy;
    this.key = key;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfoAgrmntsInner ty(String ty) {
    this.ty = ty;
    return this;
  }

  /**
   * Type of the agreement.
   * @return ty
   */
  @NotNull 
  @Schema(name = "ty", description = "Type of the agreement.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ty")
  public String getTy() {
    return ty;
  }

  public void setTy(String ty) {
    this.ty = ty;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfoAgrmntsInner sTy(String sTy) {
    this.sTy = sTy;
    return this;
  }

  /**
   * Subtype of the agreement.
   * @return sTy
   */
  @NotNull 
  @Schema(name = "sTy", description = "Subtype of the agreement.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sTy")
  public String getsTy() {
    return sTy;
  }

  public void setsTy(String sTy) {
    this.sTy = sTy;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public ProdInfoAgrmntsInner key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Key of the agreement.
   * @return key
   */
  @NotNull 
  @Schema(name = "key", description = "Key of the agreement.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("key")
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProdInfoAgrmntsInner prodInfoAgrmntsInner = (ProdInfoAgrmntsInner) o;
    return Objects.equals(this.ty, prodInfoAgrmntsInner.ty) &&
        Objects.equals(this.sTy, prodInfoAgrmntsInner.sTy) &&
        Objects.equals(this.key, prodInfoAgrmntsInner.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ty, sTy, key);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProdInfoAgrmntsInner {\n");
    sb.append("    ty: ").append(toIndentedString(ty)).append("\n");
    sb.append("    sTy: ").append(toIndentedString(sTy)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
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

