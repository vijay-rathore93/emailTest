package com.emailService.model;

import lombok.Data;

@Data
public class OrderCancellationDTO {

	private String vendorEmail;
	private String customerEmail;
	private String customerName;
	private String orderNumber;
	//private String lastfourDigitOfAccount;
	
	

}
