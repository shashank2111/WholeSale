package com.example.Trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.Trial.model.Agent;


@Component
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(Agent agent) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(agent.getEmail());
		message.setText("You have successfully registered");
		message.setSubject("Email Registration"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
		 
	}
	
	
}
