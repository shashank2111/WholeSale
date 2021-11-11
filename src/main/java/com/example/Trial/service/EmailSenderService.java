package com.example.Trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.Trial.model.Agent;
import com.example.Trial.model.Customer;


@Component
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void sendEmail(Agent agent) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(agent.getEmail());
		message.setText("You have successfully registered on ShivSansar WholeSale Market as a supplier.");
		message.setSubject("Registration for ShivSansar WholeSale Market"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
		 
	}
	
	@Async
	public void sendImportReceivedSuccessEmail(Agent agent,int requestID) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(agent.getEmail());
		message.setText("The Import has been recieved successfully with the requestID = " + Integer.toString(requestID) );
		message.setSubject("Import Request fulfilled successfully"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
	public void sendAgentUnsubscribedSuccessEmail(Agent agent) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(agent.getEmail());
		message.setText("You have been successfully unsubscribed from the ShivSansar WholeSale Portal as a supplier");
		message.setSubject("Unsubscribed from ShivSansar Market"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
	public void sendAgentDataUpdateSuccessEmail(Agent agent) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(agent.getEmail());
		message.setText("Your data has been successfully updated from the ShivSansar WholeSale Portal as a supplier in accordance with the privacy policy");
		message.setSubject("Update Successful"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
	public void sendOrderSuccessEmail(Customer customer) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(customer.getEmail());
		message.setText("Your order is successfull.");
		message.setSubject("Successfull order"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
	public void sendOrderShippedEmail(String email,int orderID) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(email);
		message.setText("Your order with orderID = " + Integer.toString(orderID) + " has been shipped to your address and it will be be received in an approximate 3 days");
		message.setSubject("Congrats, Your shipment should be arriving soon"); 
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
	public void sendOrderReceivedEmail(String email,int orderID) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(email);
		message.setText("Your order with orderID = " + Integer.toString(orderID) + " has been delieverd to your address successfully .You can contact us if there are any grievences.");
		message.setSubject("Congrats, Your shipment has been delievered."); 
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
	public void sendCustomerRegistraionSuccessEmail(Customer customer) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(customer.getEmail());
		message.setText("You have successfully registered on the portal");
		message.setSubject("Registration successful"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
	public void sendCustomerEditSuccessEmail(Customer customer) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(customer.getEmail());
		message.setText("Your data as a customer is successfully updated on the ShivSansar WholeSale Portal in accordance with the terms of privacy");
		message.setSubject("Data Update successfull"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
	public void removeCustomerFromPortalEmail(Customer customer) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shashanka2306@gmail.com");
		message.setTo(customer.getEmail());
		message.setText("You have been successfully removed from our portal database. You would require to register again if you want to make a purchase order from ShivSansar WholeSale Market");
		message.setSubject("Unsubscribed from our services successfully"); 
		
		
		mailSender.send(message);
		System.out.println("Mail sent successfully");
	}
	
	@Async
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
