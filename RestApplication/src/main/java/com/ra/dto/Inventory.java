package com.ra.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Inventory {
	private String productId;
	private String prodName;
	private String UOM;
	private Double availQty;
	private LocalDate availDate;

	public Inventory(String productId, String prodName, String uOM, Double availQty, LocalDate availDate) {
		super();
		this.productId = productId;
		this.prodName = prodName;
		this.UOM = uOM;
		this.availQty = availQty;
		this.availDate = availDate;
	}

}
