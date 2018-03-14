package com.target.retail.json.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductJSONResponse implements Serializable {

	private static final long serialVersionUID = -7344192598835028033L;
	@JsonProperty(value = "id")
	private String productId;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value="current_price")
	CurrentPriceJSONResponse currentprice;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CurrentPriceJSONResponse getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(CurrentPriceJSONResponse currentprice) {
		this.currentprice = currentprice;
	}

	@Override
	public String toString() {
		return "ProductJSONResponse [productId=" + productId + ", name=" + name+"]";
	}
}