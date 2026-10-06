package com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model;

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
 * Object for relationship between accounts.
 */

@Schema(name = "RapportoCollegato", description = "Object for relationship between accounts.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:54.504437400+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class RapportoCollegato {

  private @Nullable String relationshipCod;

  private @Nullable String officeCod;

  private @Nullable String accountKey;

  @com.fasterxml.jackson.annotation.JsonIgnore
  public RapportoCollegato relationshipCod(@Nullable String relationshipCod) {
    this.relationshipCod = relationshipCod;
    return this;
  }

  /**
   * Transparency/POS transaction/deposit account/fees and charges.
   * @return relationshipCod
   */
  
  @Schema(name = "relationshipCod", description = "Transparency/POS transaction/deposit account/fees and charges.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("relationshipCod")
  public @Nullable String getRelationshipCod() {
    return relationshipCod;
  }

  public void setRelationshipCod(@Nullable String relationshipCod) {
    this.relationshipCod = relationshipCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public RapportoCollegato officeCod(@Nullable String officeCod) {
    this.officeCod = officeCod;
    return this;
  }

  /**
   * Agency\\Office bank code.
   * @return officeCod
   */
  @Size(min = 5, max = 5) 
  @Schema(name = "officeCod", example = "12345", description = "Agency\\Office bank code.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("officeCod")
  public @Nullable String getOfficeCod() {
    return officeCod;
  }

  public void setOfficeCod(@Nullable String officeCod) {
    this.officeCod = officeCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public RapportoCollegato accountKey(@Nullable String accountKey) {
    this.accountKey = accountKey;
    return this;
  }

  /**
   * Account key.
   * @return accountKey
   */
  
  @Schema(name = "accountKey", description = "Account key.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountKey")
  public @Nullable String getAccountKey() {
    return accountKey;
  }

  public void setAccountKey(@Nullable String accountKey) {
    this.accountKey = accountKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RapportoCollegato rapportoCollegato = (RapportoCollegato) o;
    return Objects.equals(this.relationshipCod, rapportoCollegato.relationshipCod) &&
        Objects.equals(this.officeCod, rapportoCollegato.officeCod) &&
        Objects.equals(this.accountKey, rapportoCollegato.accountKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(relationshipCod, officeCod, accountKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RapportoCollegato {\n");
    sb.append("    relationshipCod: ").append(toIndentedString(relationshipCod)).append("\n");
    sb.append("    officeCod: ").append(toIndentedString(officeCod)).append("\n");
    sb.append("    accountKey: ").append(toIndentedString(accountKey)).append("\n");
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

