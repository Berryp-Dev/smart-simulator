package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
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
 * SrvAcntKey
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SrvAcntKey {

  private String bankCod;

  private String srvCod;

  private String key;

  public SrvAcntKey() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SrvAcntKey(String bankCod, String srvCod, String key) {
    this.bankCod = bankCod;
    this.srvCod = srvCod;
    this.key = key;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvAcntKey bankCod(String bankCod) {
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
  public SrvAcntKey srvCod(String srvCod) {
    this.srvCod = srvCod;
    return this;
  }

  /**
   * Service code for the settlement account.
   * @return srvCod
   */
  @NotNull 
  @Schema(name = "srvCod", description = "Service code for the settlement account.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("srvCod")
  public String getSrvCod() {
    return srvCod;
  }

  public void setSrvCod(String srvCod) {
    this.srvCod = srvCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvAcntKey key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Key of the settlement account service.
   * @return key
   */
  @NotNull 
  @Schema(name = "key", description = "Key of the settlement account service.", requiredMode = Schema.RequiredMode.REQUIRED)
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
    SrvAcntKey srvAcntKey = (SrvAcntKey) o;
    return Objects.equals(this.bankCod, srvAcntKey.bankCod) &&
        Objects.equals(this.srvCod, srvAcntKey.srvCod) &&
        Objects.equals(this.key, srvAcntKey.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankCod, srvCod, key);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SrvAcntKey {\n");
    sb.append("    bankCod: ").append(toIndentedString(bankCod)).append("\n");
    sb.append("    srvCod: ").append(toIndentedString(srvCod)).append("\n");
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

