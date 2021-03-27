package com.emailService.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseDTO {
	private final String message;
	private final Integer code;

}
