package com.example.Trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.Trial.model.Agent;
import com.example.Trial.model.Customer;


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
	
	public void sendOrderSuccessEmail(Customer customer) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(customer.getEmail());
		message.setText("Your order is successfull.");
		message.setSubject("Successfull order"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	public void sendAgentDataEdit(Agent agent) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(agent.getEmail());
		message.setText("Your data has been edited.");
		message.setSubject("Agent data Update"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	public void sendCustomerRegistraionSuccessEmail(Customer customer) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(customer.getEmail());
		message.setText("You have successfully registered on the portal");
		message.setSubject("Registration successful"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	public void sendOrderDeletionEmail(Customer customer,int orderID) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(customer.getEmail());
		String msg = "Your Order with orderID : " +  orderID + " is successfully cancelled";
		message.setText(msg);
		message.setSubject("Order Cancellation");  
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
}
