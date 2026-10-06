package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SimpleProduct;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvAcntExtKey;
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
 * CanBeCreatedRequestPayload
 */

@JsonTypeName("CanBeCreatedRequest_payload")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class CanBeCreatedRequestPayload {

  private @Nullable Object additionalUserData;

  private @Nullable SrvAcntExtKey settlementSrvActn;

  @Valid
  private List<@Valid SimpleProduct> srvActnPreDatas = new ArrayList<>();

  public CanBeCreatedRequestPayload() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CanBeCreatedRequestPayload(List<@Valid SimpleProduct> srvActnPreDatas) {
    this.srvActnPreDatas = srvActnPreDatas;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedRequestPayload additionalUserData(@Nullable Object additionalUserData) {
    this.additionalUserData = additionalUserData;
    return this;
  }

  /**
   * Additional user data for checks. Known properties include: - ivaasEnable: user has IVAAS enabled (true/false) 
   * @return additionalUserData
   */
  
  @Schema(name = "additionalUserData", description = "Additional user data for checks. Known properties include: - ivaasEnable: user has IVAAS enabled (true/false) ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalUserData")
  public @Nullable Object getAdditionalUserData() {
    return additionalUserData;
  }

  public void setAdditionalUserData(@Nullable Object additionalUserData) {
    this.additionalUserData = additionalUserData;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedRequestPayload settlementSrvActn(@Nullable SrvAcntExtKey settlementSrvActn) {
    this.settlementSrvActn = settlementSrvActn;
    return this;
  }

  /**
   * Get settlementSrvActn
   * @return settlementSrvActn
   */
  @Valid 
  @Schema(name = "settlementSrvActn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("settlementSrvActn")
  public @Nullable SrvAcntExtKey getSettlementSrvActn() {
    return settlementSrvActn;
  }

  public void setSettlementSrvActn(@Nullable SrvAcntExtKey settlementSrvActn) {
    this.settlementSrvActn = settlementSrvActn;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public CanBeCreatedRequestPayload srvActnPreDatas(List<@Valid SimpleProduct> srvActnPreDatas) {
    this.srvActnPreDatas = srvActnPreDatas;
    return this;
  }

  public CanBeCreatedRequestPayload addSrvActnPreDatasItem(SimpleProduct srvActnPreDatasItem) {
    if (this.srvActnPreDatas == null) {
      this.srvActnPreDatas = new ArrayList<>();
    }
    this.srvActnPreDatas.add(srvActnPreDatasItem);
    return this;
  }

  /**
   * List of service account data for pre-creation checks.
   * @return srvActnPreDatas
   */
  @NotNull @Valid 
  @Schema(name = "srvActnPreDatas", description = "List of service account data for pre-creation checks.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("srvActnPreDatas")
  public List<@Valid SimpleProduct> getSrvActnPreDatas() {
    return srvActnPreDatas;
  }

  public void setSrvActnPreDatas(List<@Valid SimpleProduct> srvActnPreDatas) {
    this.srvActnPreDatas = srvActnPreDatas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CanBeCreatedRequestPayload canBeCreatedRequestPayload = (CanBeCreatedRequestPayload) o;
    return Objects.equals(this.additionalUserData, canBeCreatedRequestPayload.additionalUserData) &&
        Objects.equals(this.settlementSrvActn, canBeCreatedRequestPayload.settlementSrvActn) &&
        Objects.equals(this.srvActnPreDatas, canBeCreatedRequestPayload.srvActnPreDatas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(additionalUserData, settlementSrvActn, srvActnPreDatas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CanBeCreatedRequestPayload {\n");
    sb.append("    additionalUserData: ").append(toIndentedString(additionalUserData)).append("\n");
    sb.append("    settlementSrvActn: ").append(toIndentedString(settlementSrvActn)).append("\n");
    sb.append("    srvActnPreDatas: ").append(toIndentedString(srvActnPreDatas)).append("\n");
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

