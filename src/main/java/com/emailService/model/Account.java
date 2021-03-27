package com.emailService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	private Integer id;
	private String username;
	private String fullName;
	private String address;
	private String email;
	private String phone;
	private String url;

}
