package com.emailService.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.emailService.model.Account;
import com.emailService.model.OrderCancellationDTO;
import com.emailService.model.ProductDetails;
import com.emailService.model.ResponseDTO;
import com.emailService.service.EmailService;
import com.emailService.utility.EmailMessages;
import com.emailService.utility.ReportExtractor;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequiredArgsConstructor
public class EmailController {

	private final EmailService emailService;
	private final ReportExtractor reportExtractor;

	@PostMapping("/activationMail")
	public ResponseEntity<ResponseDTO> sendMailForResetPass(@RequestHeader("email") String email,@RequestHeader("url") String url)
			throws MessagingException, IOException {
		emailService.sendMailForVerification(url,email,EmailMessages.ACTIVATION_LINK_SUBJECT,"confirmEmail");
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(EmailMessages.ACTIVATION_LINK_SUCCESS, HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@PostMapping("/passwordChange")
	public ResponseEntity<ResponseDTO> sendMailForresetPassword(@RequestHeader("email") String email,@RequestHeader("url") String url)
			throws MessagingException, IOException {
		emailService.sendMailForVerification(url,email,EmailMessages.RESET_PASSWORD_LINK_SUBJECT,"resetPasswordd");
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(EmailMessages.RESET_PASSWORD_LINK_SUCCESS, HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@PostMapping("/orderPlaced")
	public ResponseEntity<ResponseDTO> sendMailForOrderPlaced(@RequestHeader("email") String email,@RequestHeader("url") String url)
			throws MessagingException, IOException {
		emailService.sendMailForVerification(url,email,EmailMessages.ORDER_CONFIRMATION_LINK_SUBJECT,"orderCancellation");
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(EmailMessages.ORDER_CONFIRMATION_LINK_SUCCESS, HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@PostMapping("/orderCancellation")
	public ResponseEntity<ResponseDTO> sendMailForOrderCancellation(
			@RequestBody OrderCancellationDTO OrderCancellationDTO) throws MessagingException, IOException {
		emailService.sendMailForOrderCancellation(OrderCancellationDTO,EmailMessages.ORDER_CANCELLATION_LINK_SUBJECT);
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(EmailMessages.ORDER_CANCELLATION_LINK_SUCCESS, HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@PostMapping("/replacementItm")
	public ResponseEntity<ResponseDTO> sendMailForReplacementItm(@RequestHeader("email") String email,@RequestHeader("url") String url)
			throws MessagingException, IOException {
		emailService.sendMailForVerification(url,email,EmailMessages.ORDER_REPLACEMENT_LINK_SUBJECT,"orderCancellation");
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(EmailMessages.ORDER_REPLACEMENT_LINK_SUCCESS, HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@GetMapping("/generateReport")
	public ResponseEntity<String> sendMailForReplacementItm() throws MessagingException, IOException, JRException {

		List<ProductDetails> list = new ArrayList<ProductDetails>(
				Arrays.asList(new ProductDetails( "Mobile",1000, 1, "No", new Date().toString()),
						new ProductDetails( "Bat", 1000,1, "No", new Date().toString()),
						new ProductDetails( "Fashion Items",1000, 1, "No", new Date().toString()),
						new ProductDetails("Mobile 2", 1000, 1, "No", new Date().toString())));

		reportExtractor.exportReport("pdf", list);
		return new ResponseEntity<String>("Done", HttpStatus.OK);
	}

}
