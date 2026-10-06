package com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvAcntExtKey;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.model.SrvAcntKey;
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
 * SetSettlementServiceAccountKeyDtoRequestPayload
 */

@JsonTypeName("SetSettlementServiceAccountKeyDtoRequest_payload")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:49.813398600+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SetSettlementServiceAccountKeyDtoRequestPayload {

  private String caseID;

  private SrvAcntKey srvAcntKey;

  private SrvAcntExtKey settlementAccountSrv;

  public SetSettlementServiceAccountKeyDtoRequestPayload() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SetSettlementServiceAccountKeyDtoRequestPayload(String caseID, SrvAcntKey srvAcntKey, SrvAcntExtKey settlementAccountSrv) {
    this.caseID = caseID;
    this.srvAcntKey = srvAcntKey;
    this.settlementAccountSrv = settlementAccountSrv;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SetSettlementServiceAccountKeyDtoRequestPayload caseID(String caseID) {
    this.caseID = caseID;
    return this;
  }

  /**
   * ID of the Case.
   * @return caseID
   */
  @NotNull 
  @Schema(name = "caseID", example = "123e4567-e89b-12d3-a456-426614174000", description = "ID of the Case.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("caseID")
  public String getCaseID() {
    return caseID;
  }

  public void setCaseID(String caseID) {
    this.caseID = caseID;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SetSettlementServiceAccountKeyDtoRequestPayload srvAcntKey(SrvAcntKey srvAcntKey) {
    this.srvAcntKey = srvAcntKey;
    return this;
  }

  /**
   * Get srvAcntKey
   * @return srvAcntKey
   */
  @NotNull @Valid 
  @Schema(name = "srvAcntKey", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("srvAcntKey")
  public SrvAcntKey getSrvAcntKey() {
    return srvAcntKey;
  }

  public void setSrvAcntKey(SrvAcntKey srvAcntKey) {
    this.srvAcntKey = srvAcntKey;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SetSettlementServiceAccountKeyDtoRequestPayload settlementAccountSrv(SrvAcntExtKey settlementAccountSrv) {
    this.settlementAccountSrv = settlementAccountSrv;
    return this;
  }

  /**
   * Get settlementAccountSrv
   * @return settlementAccountSrv
   */
  @NotNull @Valid 
  @Schema(name = "settlementAccountSrv", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("settlementAccountSrv")
  public SrvAcntExtKey getSettlementAccountSrv() {
    return settlementAccountSrv;
  }

  public void setSettlementAccountSrv(SrvAcntExtKey settlementAccountSrv) {
    this.settlementAccountSrv = settlementAccountSrv;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetSettlementServiceAccountKeyDtoRequestPayload setSettlementServiceAccountKeyDtoRequestPayload = (SetSettlementServiceAccountKeyDtoRequestPayload) o;
    return Objects.equals(this.caseID, setSettlementServiceAccountKeyDtoRequestPayload.caseID) &&
        Objects.equals(this.srvAcntKey, setSettlementServiceAccountKeyDtoRequestPayload.srvAcntKey) &&
        Objects.equals(this.settlementAccountSrv, setSettlementServiceAccountKeyDtoRequestPayload.settlementAccountSrv);
  }

  @Override
  public int hashCode() {
    return Objects.hash(caseID, srvAcntKey, settlementAccountSrv);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetSettlementServiceAccountKeyDtoRequestPayload {\n");
    sb.append("    caseID: ").append(toIndentedString(caseID)).append("\n");
    sb.append("    srvAcntKey: ").append(toIndentedString(srvAcntKey)).append("\n");
    sb.append("    settlementAccountSrv: ").append(toIndentedString(settlementAccountSrv)).append("\n");
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

