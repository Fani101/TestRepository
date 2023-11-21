package com.ra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
	private String productId;
	private String prodName;
	private double availQty;

	public ProductResponse(String productId, String prodName,  double availQty) {
		super();
		this.productId = productId;
		this.prodName = prodName;
		this.availQty = availQty;
	}

}
