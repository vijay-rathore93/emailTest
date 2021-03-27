package com.emailService.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.emailService.model.Account;
import com.emailService.model.OrderCancellationDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

	private final SpringTemplateEngine templateEngine;

	public void sendMailForVerification(Account account, String subject,String templateName) throws MessagingException {
		final Context context = new Context();
		context.setVariable("url", account.getUrl());
		String body = templateEngine.process(templateName, context);
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(account.getEmail());
		helper.setSubject(subject);
		helper.setText(body, true);
		mailSender.send(mail);
	}

	public void sendMailForOrderCancellation(OrderCancellationDTO OrderCancellationDTO, String subject)
			throws MessagingException {
		final Context context = new Context();
		context.setVariable("user", OrderCancellationDTO.getCustomerName());
		context.setVariable("orderNumber", OrderCancellationDTO.getOrderNumber());
		String body = templateEngine.process("orderCancellation", context);
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(OrderCancellationDTO.getCustomerEmail());
		helper.setSubject(subject);
		helper.setText(body, true);
		mailSender.send(mail);
	}

}
