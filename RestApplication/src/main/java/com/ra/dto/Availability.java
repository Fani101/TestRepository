package com.ra.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Availability {
	private String storeNo;
	private String productId;
	private Date date;
	private Double availQty;
}
