package com.ra.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Capacity {
	private String storeNo;
	private Date date;
	private Double noOfOrdersAccepted;
}
