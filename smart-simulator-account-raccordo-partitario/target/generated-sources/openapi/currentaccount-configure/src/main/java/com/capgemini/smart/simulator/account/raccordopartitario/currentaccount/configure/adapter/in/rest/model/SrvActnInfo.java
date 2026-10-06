package com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model.Currency;
import com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model.RapportoCollegato;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Internal object for all fields different from srvAcntKey.
 */

@Schema(name = "SrvActnInfo", description = "Internal object for all fields different from srvAcntKey.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:54.504437400+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public class SrvActnInfo {

  private String branchCode;

  private @Nullable String operationalProduct;

  private String commercialProduct;

  private Currency currency;

  private @Nullable String origination;

  private String natureAndScopeCod;

  private @Nullable String riskCode;

  private String offer;

  private @Nullable String agreement;

  @Valid
  private List<@Valid RapportoCollegato> linkedAccount = new ArrayList<>();

  private @Nullable String linkedNdg;

  private @Nullable String linkedNdgRelationType;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime accountExpiryDate;

  private @Nullable String anatocismo;

  private @Nullable String settlementFrequency;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime settlementDate;

  private @Nullable String linkedCredit;

  private @Nullable Double firstDepositAmount;

  private @Nullable Boolean omitCheckbook;

  public SrvActnInfo() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SrvActnInfo(String branchCode, String commercialProduct, Currency currency, String natureAndScopeCod, String offer) {
    this.branchCode = branchCode;
    this.commercialProduct = commercialProduct;
    this.currency = currency;
    this.natureAndScopeCod = natureAndScopeCod;
    this.offer = offer;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo branchCode(String branchCode) {
    this.branchCode = branchCode;
    return this;
  }

  /**
   * Branch where the account is opened.
   * @return branchCode
   */
  @NotNull 
  @Schema(name = "branchCode", description = "Branch where the account is opened.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("branchCode")
  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo operationalProduct(@Nullable String operationalProduct) {
    this.operationalProduct = operationalProduct;
    return this;
  }

  /**
   * Deprecated and optional because of present in creation. Operational product to be opened. Ignored in implementation
   * @return operationalProduct
   */
  
  @Schema(name = "operationalProduct", description = "Deprecated and optional because of present in creation. Operational product to be opened. Ignored in implementation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("operationalProduct")
  public @Nullable String getOperationalProduct() {
    return operationalProduct;
  }

  public void setOperationalProduct(@Nullable String operationalProduct) {
    this.operationalProduct = operationalProduct;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo commercialProduct(String commercialProduct) {
    this.commercialProduct = commercialProduct;
    return this;
  }

  /**
   * Commercial product code to be opened. For ToMe, either fixed value 'A37094' or retrieved from catalog.
   * @return commercialProduct
   */
  @NotNull 
  @Schema(name = "commercialProduct", description = "Commercial product code to be opened. For ToMe, either fixed value 'A37094' or retrieved from catalog.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("commercialProduct")
  public String getCommercialProduct() {
    return commercialProduct;
  }

  public void setCommercialProduct(String commercialProduct) {
    this.commercialProduct = commercialProduct;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo currency(Currency currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
   */
  @NotNull @Valid 
  @Schema(name = "currency", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("currency")
  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo origination(@Nullable String origination) {
    this.origination = origination;
    return this;
  }

  /**
   * For ToMe, to be verified if fixed value or other with BSNS.
   * @return origination
   */
  
  @Schema(name = "origination", description = "For ToMe, to be verified if fixed value or other with BSNS.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("origination")
  public @Nullable String getOrigination() {
    return origination;
  }

  public void setOrigination(@Nullable String origination) {
    this.origination = origination;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo natureAndScopeCod(String natureAndScopeCod) {
    this.natureAndScopeCod = natureAndScopeCod;
    return this;
  }

  /**
   * Nature and scope of the account; For ToMe, to be verified if fixed value or other with BSNS.
   * @return natureAndScopeCod
   */
  @NotNull 
  @Schema(name = "natureAndScopeCod", description = "Nature and scope of the account; For ToMe, to be verified if fixed value or other with BSNS.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("natureAndScopeCod")
  public String getNatureAndScopeCod() {
    return natureAndScopeCod;
  }

  public void setNatureAndScopeCod(String natureAndScopeCod) {
    this.natureAndScopeCod = natureAndScopeCod;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo riskCode(@Nullable String riskCode) {
    this.riskCode = riskCode;
    return this;
  }

  /**
   * If missing, assigned by the ledger with default value.
   * @return riskCode
   */
  
  @Schema(name = "riskCode", description = "If missing, assigned by the ledger with default value.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("riskCode")
  public @Nullable String getRiskCode() {
    return riskCode;
  }

  public void setRiskCode(@Nullable String riskCode) {
    this.riskCode = riskCode;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo offer(String offer) {
    this.offer = offer;
    return this;
  }

  /**
   * For ToMe, either fixed value 'OFFCCO99' or retrieved from catalog.
   * @return offer
   */
  @NotNull 
  @Schema(name = "offer", description = "For ToMe, either fixed value 'OFFCCO99' or retrieved from catalog.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("offer")
  public String getOffer() {
    return offer;
  }

  public void setOffer(String offer) {
    this.offer = offer;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo agreement(@Nullable String agreement) {
    this.agreement = agreement;
    return this;
  }

  /**
   * Agreement.
   * @return agreement
   */
  
  @Schema(name = "agreement", description = "Agreement.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("agreement")
  public @Nullable String getAgreement() {
    return agreement;
  }

  public void setAgreement(@Nullable String agreement) {
    this.agreement = agreement;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo linkedAccount(List<@Valid RapportoCollegato> linkedAccount) {
    this.linkedAccount = linkedAccount;
    return this;
  }

  public SrvActnInfo addLinkedAccountItem(RapportoCollegato linkedAccountItem) {
    if (this.linkedAccount == null) {
      this.linkedAccount = new ArrayList<>();
    }
    this.linkedAccount.add(linkedAccountItem);
    return this;
  }

  /**
   * Accounts linked to the one being opened.
   * @return linkedAccount
   */
  @Valid 
  @Schema(name = "linkedAccount", description = "Accounts linked to the one being opened.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("linkedAccount")
  public List<@Valid RapportoCollegato> getLinkedAccount() {
    return linkedAccount;
  }

  public void setLinkedAccount(List<@Valid RapportoCollegato> linkedAccount) {
    this.linkedAccount = linkedAccount;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo linkedNdg(@Nullable String linkedNdg) {
    this.linkedNdg = linkedNdg;
    return this;
  }

  /**
   * Only one occurrence for subscriber or company owner; attorneys/delegates are added in post-sale.
   * @return linkedNdg
   */
  
  @Schema(name = "linkedNdg", description = "Only one occurrence for subscriber or company owner; attorneys/delegates are added in post-sale.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("linkedNdg")
  public @Nullable String getLinkedNdg() {
    return linkedNdg;
  }

  public void setLinkedNdg(@Nullable String linkedNdg) {
    this.linkedNdg = linkedNdg;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo linkedNdgRelationType(@Nullable String linkedNdgRelationType) {
    this.linkedNdgRelationType = linkedNdgRelationType;
    return this;
  }

  /**
   * Type of relationship. (18 for subscriber or 30 for company owner).
   * @return linkedNdgRelationType
   */
  
  @Schema(name = "linkedNdgRelationType", description = "Type of relationship. (18 for subscriber or 30 for company owner).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("linkedNdgRelationType")
  public @Nullable String getLinkedNdgRelationType() {
    return linkedNdgRelationType;
  }

  public void setLinkedNdgRelationType(@Nullable String linkedNdgRelationType) {
    this.linkedNdgRelationType = linkedNdgRelationType;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo accountExpiryDate(@Nullable OffsetDateTime accountExpiryDate) {
    this.accountExpiryDate = accountExpiryDate;
    return this;
  }

  /**
   * The date time in RFC 3339 format.
   * @return accountExpiryDate
   */
  @Valid 
  @Schema(name = "accountExpiryDate", example = "2024-01-18T15:04:05Z", description = "The date time in RFC 3339 format.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountExpiryDate")
  public @Nullable OffsetDateTime getAccountExpiryDate() {
    return accountExpiryDate;
  }

  public void setAccountExpiryDate(@Nullable OffsetDateTime accountExpiryDate) {
    this.accountExpiryDate = accountExpiryDate;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo anatocismo(@Nullable String anatocismo) {
    this.anatocismo = anatocismo;
    return this;
  }

  /**
   * Compound interest flag.
   * @return anatocismo
   */
  
  @Schema(name = "anatocismo", description = "Compound interest flag.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("anatocismo")
  public @Nullable String getAnatocismo() {
    return anatocismo;
  }

  public void setAnatocismo(@Nullable String anatocismo) {
    this.anatocismo = anatocismo;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo settlementFrequency(@Nullable String settlementFrequency) {
    this.settlementFrequency = settlementFrequency;
    return this;
  }

  /**
   * Interest settlement frequency.
   * @return settlementFrequency
   */
  
  @Schema(name = "settlementFrequency", description = "Interest settlement frequency.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("settlementFrequency")
  public @Nullable String getSettlementFrequency() {
    return settlementFrequency;
  }

  public void setSettlementFrequency(@Nullable String settlementFrequency) {
    this.settlementFrequency = settlementFrequency;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo settlementDate(@Nullable OffsetDateTime settlementDate) {
    this.settlementDate = settlementDate;
    return this;
  }

  /**
   * The date time in RFC 3339 format.
   * @return settlementDate
   */
  @Valid 
  @Schema(name = "settlementDate", example = "2024-01-18T15:04:05Z", description = "The date time in RFC 3339 format.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("settlementDate")
  public @Nullable OffsetDateTime getSettlementDate() {
    return settlementDate;
  }

  public void setSettlementDate(@Nullable OffsetDateTime settlementDate) {
    this.settlementDate = settlementDate;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo linkedCredit(@Nullable String linkedCredit) {
    this.linkedCredit = linkedCredit;
    return this;
  }

  /**
   * Linked credit.
   * @return linkedCredit
   */
  
  @Schema(name = "linkedCredit", description = "Linked credit.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("linkedCredit")
  public @Nullable String getLinkedCredit() {
    return linkedCredit;
  }

  public void setLinkedCredit(@Nullable String linkedCredit) {
    this.linkedCredit = linkedCredit;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo firstDepositAmount(@Nullable Double firstDepositAmount) {
    this.firstDepositAmount = firstDepositAmount;
    return this;
  }

  /**
   * First deposit amount.
   * @return firstDepositAmount
   */
  
  @Schema(name = "firstDepositAmount", description = "First deposit amount.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firstDepositAmount")
  public @Nullable Double getFirstDepositAmount() {
    return firstDepositAmount;
  }

  public void setFirstDepositAmount(@Nullable Double firstDepositAmount) {
    this.firstDepositAmount = firstDepositAmount;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  public SrvActnInfo omitCheckbook(@Nullable Boolean omitCheckbook) {
    this.omitCheckbook = omitCheckbook;
    return this;
  }

  /**
   * disable Checkbook production. Default is false (Checkbook production enabled).
   * @return omitCheckbook
   */
  
  @Schema(name = "omitCheckbook", description = "disable Checkbook production. Default is false (Checkbook production enabled).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("omitCheckbook")
  public @Nullable Boolean getOmitCheckbook() {
    return omitCheckbook;
  }

  public void setOmitCheckbook(@Nullable Boolean omitCheckbook) {
    this.omitCheckbook = omitCheckbook;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SrvActnInfo srvActnInfo = (SrvActnInfo) o;
    return Objects.equals(this.branchCode, srvActnInfo.branchCode) &&
        Objects.equals(this.operationalProduct, srvActnInfo.operationalProduct) &&
        Objects.equals(this.commercialProduct, srvActnInfo.commercialProduct) &&
        Objects.equals(this.currency, srvActnInfo.currency) &&
        Objects.equals(this.origination, srvActnInfo.origination) &&
        Objects.equals(this.natureAndScopeCod, srvActnInfo.natureAndScopeCod) &&
        Objects.equals(this.riskCode, srvActnInfo.riskCode) &&
        Objects.equals(this.offer, srvActnInfo.offer) &&
        Objects.equals(this.agreement, srvActnInfo.agreement) &&
        Objects.equals(this.linkedAccount, srvActnInfo.linkedAccount) &&
        Objects.equals(this.linkedNdg, srvActnInfo.linkedNdg) &&
        Objects.equals(this.linkedNdgRelationType, srvActnInfo.linkedNdgRelationType) &&
        Objects.equals(this.accountExpiryDate, srvActnInfo.accountExpiryDate) &&
        Objects.equals(this.anatocismo, srvActnInfo.anatocismo) &&
        Objects.equals(this.settlementFrequency, srvActnInfo.settlementFrequency) &&
        Objects.equals(this.settlementDate, srvActnInfo.settlementDate) &&
        Objects.equals(this.linkedCredit, srvActnInfo.linkedCredit) &&
        Objects.equals(this.firstDepositAmount, srvActnInfo.firstDepositAmount) &&
        Objects.equals(this.omitCheckbook, srvActnInfo.omitCheckbook);
  }

  @Override
  public int hashCode() {
    return Objects.hash(branchCode, operationalProduct, commercialProduct, currency, origination, natureAndScopeCod, riskCode, offer, agreement, linkedAccount, linkedNdg, linkedNdgRelationType, accountExpiryDate, anatocismo, settlementFrequency, settlementDate, linkedCredit, firstDepositAmount, omitCheckbook);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SrvActnInfo {\n");
    sb.append("    branchCode: ").append(toIndentedString(branchCode)).append("\n");
    sb.append("    operationalProduct: ").append(toIndentedString(operationalProduct)).append("\n");
    sb.append("    commercialProduct: ").append(toIndentedString(commercialProduct)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    origination: ").append(toIndentedString(origination)).append("\n");
    sb.append("    natureAndScopeCod: ").append(toIndentedString(natureAndScopeCod)).append("\n");
    sb.append("    riskCode: ").append(toIndentedString(riskCode)).append("\n");
    sb.append("    offer: ").append(toIndentedString(offer)).append("\n");
    sb.append("    agreement: ").append(toIndentedString(agreement)).append("\n");
    sb.append("    linkedAccount: ").append(toIndentedString(linkedAccount)).append("\n");
    sb.append("    linkedNdg: ").append(toIndentedString(linkedNdg)).append("\n");
    sb.append("    linkedNdgRelationType: ").append(toIndentedString(linkedNdgRelationType)).append("\n");
    sb.append("    accountExpiryDate: ").append(toIndentedString(accountExpiryDate)).append("\n");
    sb.append("    anatocismo: ").append(toIndentedString(anatocismo)).append("\n");
    sb.append("    settlementFrequency: ").append(toIndentedString(settlementFrequency)).append("\n");
    sb.append("    settlementDate: ").append(toIndentedString(settlementDate)).append("\n");
    sb.append("    linkedCredit: ").append(toIndentedString(linkedCredit)).append("\n");
    sb.append("    firstDepositAmount: ").append(toIndentedString(firstDepositAmount)).append("\n");
    sb.append("    omitCheckbook: ").append(toIndentedString(omitCheckbook)).append("\n");
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

