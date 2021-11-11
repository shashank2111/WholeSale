package com.example.Trial.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Trial.model.Customer;
import com.example.Trial.service.CustomerService;
import com.example.Trial.service.EmailSenderService;

@Controller
public class AdminCustomerController {
	
	@Autowired
	private CustomerService customerServiceImpl;
	
	@Autowired
	private EmailSenderService emailService;
	
	@GetMapping("/customer")
	public String handleManageCustomer(Principal principal,Model model) {
		model.addAttribute("message","You are in managecustomer");
		model.addAttribute("email",principal.getName());
//		System.out.println(redirectAttributes.getAttribute("customers"));
//		model.addAttribute("customers",redirectAttributes.getAttribute("customers"));
//		System.out.println(model.getAttribute("customers"));
//		model.addAttribute("customers",customers);
		return "customermanagement";
	}
	
	@PostMapping("/customer")
	public String handleAddCustomer(@ModelAttribute("customer") Customer customer ,Model model) {
		
		int customerID = customerServiceImpl.addCustomer(customer);
		System.out.println("added the customer");
//		model.addAttribute("successmessage","The customer has been registered successfully");
		model.addAttribute("success",true);
		model.addAttribute("customerID",customerID);
		
		emailService.sendCustomerRegistraionSuccessEmail(customer);
		return "customermanagement";
		
	}	
	
	@PostMapping("/customer/search")
	public String handleSearchCustomer(@ModelAttribute("customer")  Customer customer,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes,Model model) {
		
		 
		System.out.println(customer.getEmail());
		System.out.println(customer.getCustomerID());
		List<Customer> customers = customerServiceImpl.findAllCustomersByCustomer(customer);
		
		redirectAttributes.addFlashAttribute("customers",customers);
//		System.out.println(redirectAttributes.getAttribute("customers"));
		return "redirect:/customer"; 
	}
	
//	@GetMapping("/customer/edit")
//	public String handleEditCustomer(@ModelAttribute("Customer") Customer customer,BindingResult bidingResult,Model model,Principal principal) {
//		
//		model.addAttribute("useremail",principal.getName()); 
//		model.addAttribute("customer",customer);	
//		return "customeredit";
//	}
//	
	@PostMapping("/customer/edit")
	public String handleCurrentCustomer(@ModelAttribute("customercurrent") Customer customer,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model,Principal principal) {
		System.out.println(customer.toString());
		model.addAttribute("useremail",principal.getName()); 
		model.addAttribute("originalcustomer",customer);
		System.out.println(customer.toString());
		return "customeredit";
	}
	
	@PostMapping("/customer/edit/confirm")
	public String handleEditCustomerData(@ModelAttribute("customer") Customer customer,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {
		
		int count = customerServiceImpl.updateCustomer(customer);
		System.out.println("No of rows affected = " + Integer.toString(count));
		System.out.println("Updated the customer successfully");
		System.out.println(customer.toString());
		
		emailService.sendCustomerEditSuccessEmail(customer);
		return "redirect:/customer"; 	
	}
	
	@PostMapping("/customer/delete")
	public String handleDeleteCustomer(@ModelAttribute("customertodelete") Customer customer,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {
		
		int countOfRecord = customerServiceImpl.deleteCustomer(customer);
		System.out.println("Customer successfully deleted");
		
		
		emailService.removeCustomerFromPortalEmail(customer);
		return "redirect:/customer";
	}
	
	@GetMapping("/customer/allcustomers")
	public String handleShowAllCustomers(Model model,Principal principal) {
		List<Customer> allCustomers = customerServiceImpl.getAllCustomers();
		model.addAttribute("allCustomers", allCustomers);
		
		return "allCustomers";
	}
	
}
