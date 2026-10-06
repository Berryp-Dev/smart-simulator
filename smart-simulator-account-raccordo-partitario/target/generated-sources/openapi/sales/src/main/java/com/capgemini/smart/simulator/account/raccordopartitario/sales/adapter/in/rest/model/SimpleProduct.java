package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SimpleProductExternalProdInfo;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SimpleProductNdgInfo;
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
 * Service account data for pre-creation checks.
 */

@Schema(name = "SimpleProduct", description = "Service account data for pre-creation checks.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SimpleProduct {

  private String bankCod;

  private String srvCod;

  private String officeCod;

  private SimpleProductNdgInfo ndgInfo;

  private SimpleProductExternalProdInfo externalProdInfo;

  public SimpleProduct() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SimpleProduct(String bankCod, String srvCod, String officeCod, SimpleProductNdgInfo ndgInfo, SimpleProductExternalProdInfo externalProdInfo) {
    this.bankCod = bankCod;
    this.srvCod = srvCod;
    this.officeCod = officeCod;
    this.ndgInfo = ndgInfo;
    this.externalProdInfo = externalProdInfo;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProduct bankCod(String bankCod) {
    this.bankCod = bankCod;
    return this;
  }

  /**
   * ABI Bank code.
   * @return bankCod
   */
  @NotNull @Size(min = 5, max = 5) 
  @Schema(name = "bankCod", example = "01005", description = "ABI Bank code.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("bankCod")
  public String getBankCod() {
    return bankCod;
  }

  public void setBankCod(String bankCod) {
    this.bankCod = bankCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProduct srvCod(String srvCod) {
    this.srvCod = srvCod;
    return this;
  }

  /**
   * Service code for the service account.
   * @return srvCod
   */
  @NotNull 
  @Schema(name = "srvCod", example = "CC", description = "Service code for the service account.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("srvCod")
  public String getSrvCod() {
    return srvCod;
  }

  public void setSrvCod(String srvCod) {
    this.srvCod = srvCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProduct officeCod(String officeCod) {
    this.officeCod = officeCod;
    return this;
  }

  /**
   * Agency\\Office bank code.
   * @return officeCod
   */
  @NotNull @Size(min = 5, max = 5) 
  @Schema(name = "officeCod", example = "12345", description = "Agency\\Office bank code.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("officeCod")
  public String getOfficeCod() {
    return officeCod;
  }

  public void setOfficeCod(String officeCod) {
    this.officeCod = officeCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProduct ndgInfo(SimpleProductNdgInfo ndgInfo) {
    this.ndgInfo = ndgInfo;
    return this;
  }

  /**
   * Get ndgInfo
   * @return ndgInfo
   */
  @NotNull @Valid 
  @Schema(name = "ndgInfo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ndgInfo")
  public SimpleProductNdgInfo getNdgInfo() {
    return ndgInfo;
  }

  public void setNdgInfo(SimpleProductNdgInfo ndgInfo) {
    this.ndgInfo = ndgInfo;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SimpleProduct externalProdInfo(SimpleProductExternalProdInfo externalProdInfo) {
    this.externalProdInfo = externalProdInfo;
    return this;
  }

  /**
   * Get externalProdInfo
   * @return externalProdInfo
   */
  @NotNull @Valid 
  @Schema(name = "externalProdInfo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("externalProdInfo")
  public SimpleProductExternalProdInfo getExternalProdInfo() {
    return externalProdInfo;
  }

  public void setExternalProdInfo(SimpleProductExternalProdInfo externalProdInfo) {
    this.externalProdInfo = externalProdInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleProduct simpleProduct = (SimpleProduct) o;
    return Objects.equals(this.bankCod, simpleProduct.bankCod) &&
        Objects.equals(this.srvCod, simpleProduct.srvCod) &&
        Objects.equals(this.officeCod, simpleProduct.officeCod) &&
        Objects.equals(this.ndgInfo, simpleProduct.ndgInfo) &&
        Objects.equals(this.externalProdInfo, simpleProduct.externalProdInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankCod, srvCod, officeCod, ndgInfo, externalProdInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimpleProduct {\n");
    sb.append("    bankCod: ").append(toIndentedString(bankCod)).append("\n");
    sb.append("    srvCod: ").append(toIndentedString(srvCod)).append("\n");
    sb.append("    officeCod: ").append(toIndentedString(officeCod)).append("\n");
    sb.append("    ndgInfo: ").append(toIndentedString(ndgInfo)).append("\n");
    sb.append("    externalProdInfo: ").append(toIndentedString(externalProdInfo)).append("\n");
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

