package com.ra.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductAvailability {
	private String storeNo;
	private String productId;
	private Double reqQty;
	private Date reqDate;
	private String status;
	

}
