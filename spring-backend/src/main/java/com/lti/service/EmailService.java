package com.lti.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private JavaMailSender mailSender;
	
	public EmailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}
	
	public void sendMail(String toMail, String subject, int message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setTo(toMail);
		mailMessage.setSubject(subject);
		mailMessage.setText("Your OTP is " + message + "." + " \n\nUse this OTP to reset your password." + "\n" + "OTP is only valid for 5 minutes");
		
		mailMessage.setFrom("us.bot18@gmail");
		
		mailSender.send(mailMessage);
	}
}
