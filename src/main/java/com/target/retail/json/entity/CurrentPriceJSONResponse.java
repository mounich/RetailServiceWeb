package com.target.retail.json.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPriceJSONResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7523546004162913363L;
	@JsonProperty(value = "value")
	private Double value;
	@JsonProperty(value = "currency_code")
	private String currencycode;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
}
