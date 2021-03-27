package com.emailService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetails {

	
	private String productName;
	private Integer price;
	private Integer qty;
	private String discount;
	private String orderDate;
}
