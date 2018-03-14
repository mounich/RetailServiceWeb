package com.target.retail.json.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductJSONRequest implements Serializable {
	
	private static final long serialVersionUID = -5856752455454830896L;

	@JsonProperty(value = "id")
	private String productId;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value="current_price")
	CurrentPriceJSONRequest currentprice;

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

	public CurrentPriceJSONRequest getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(CurrentPriceJSONRequest currentprice) {
		this.currentprice = currentprice;
	}
	
	@Override
	public String toString() {
		return "ProductJSONRequest [productId=" + productId + ", name=" + name+ "]";
	}
}