package com.target.retail.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productsprices")
public class CurrentPrice implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1481787039055565483L;
	private Double value;
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