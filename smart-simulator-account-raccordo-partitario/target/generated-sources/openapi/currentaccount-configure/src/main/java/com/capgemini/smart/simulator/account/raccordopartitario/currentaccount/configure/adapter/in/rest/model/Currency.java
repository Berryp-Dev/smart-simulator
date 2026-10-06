package com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Currency code in ISO 4217 format.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-07T00:07:54.504437400+02:00[Europe/Rome]", comments = "Generator version: 7.19.0")
public enum Currency {
  
  AED("AED"),
  
  ARS("ARS"),
  
  AUD("AUD"),
  
  BRL("BRL"),
  
  CAD("CAD"),
  
  CHF("CHF"),
  
  CNY("CNY"),
  
  CZK("CZK"),
  
  DKK("DKK"),
  
  EUR("EUR"),
  
  GBP("GBP"),
  
  HKD("HKD"),
  
  HUF("HUF"),
  
  IDR("IDR"),
  
  ILS("ILS"),
  
  INR("INR"),
  
  JPY("JPY"),
  
  KRW("KRW"),
  
  MXN("MXN"),
  
  NOK("NOK"),
  
  NZD("NZD"),
  
  PHP("PHP"),
  
  PLN("PLN"),
  
  RON("RON"),
  
  RUB("RUB"),
  
  SAR("SAR"),
  
  SEK("SEK"),
  
  SGD("SGD"),
  
  THB("THB"),
  
  TRY("TRY"),
  
  USD("USD"),
  
  ZAR("ZAR");

  private final String value;

  Currency(String value) {
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
  public static Currency fromValue(String value) {
    for (Currency b : Currency.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

