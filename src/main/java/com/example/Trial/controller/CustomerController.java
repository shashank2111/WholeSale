package com.example.Trial.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Trial.model.Customer;
import com.example.Trial.model.Order;
import com.example.Trial.model.OrderDetails;
import com.example.Trial.model.OrderDetailsEditor;
import com.example.Trial.model.OrderDetailsWrapper;
import com.example.Trial.model.Product;
import com.example.Trial.service.CustomerService;
import com.example.Trial.service.EmailSenderService;
import com.example.Trial.service.OrderDetailsService;
import com.example.Trial.service.OrderService;
import com.example.Trial.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CustomerController {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private CustomerService customerServiceImpl;
	
	@Autowired
	private OrderService orderServiceImpl;

	@Autowired
	private OrderDetailsService orderDetailsServiceImpl;
	
	@Autowired
	private ProductService productServiceImpl;
	
	@Autowired
	private EmailSenderService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(OrderDetailsWrapper.class, new OrderDetailsEditor(objectMapper));
	}
	
	@GetMapping("/c/customer")
	public String handleCustomerEntrance(Principal principal,Model model) {
		model.addAttribute("email", principal.getName());	
		
		Customer customer = customerServiceImpl.getCustomerByEmail(principal.getName());
		int customerID = customer.getCustomerID();
		System.out.println("customer Id is ");
		System.out.println(customerID);
		List<Order> myPendingOrders = orderServiceImpl.getMyPendingOrders(customerID);
		List<Order> myFulfilledOrders = orderServiceImpl.getMyFulfilledOrders(customerID);
		model.addAttribute("myPendingOrders",myPendingOrders);
		for(Order o:myPendingOrders) {
			System.out.println(o.getOrderID());
			System.out.println(o.getOrderdate());
			System.out.println("\n");
		}
		
		model.addAttribute("myFulfilledOrders",myFulfilledOrders);
		for(Order od:myFulfilledOrders) {
			System.out.println(od.getOrderID());
		}
		return "customerpage";
	}
	
	@GetMapping("/c/makeorder")
	public String handleMakeOrder(Principal principal,Model model) {
		
		List<Product> allProducts = productServiceImpl.getAllProducts();
		
		model.addAttribute("allProducts",allProducts);
		model.addAttribute("email",principal.getName());
		return "makeorderpage.html";
	}
	
	@GetMapping("/c/index")
	public String tryJson(Model model) {
		
		return "index";
	}
	
	
	@PostMapping("/c/json")
	public String handleJson(@RequestParam String json,Model model,Principal principal) throws JsonMappingException, JsonProcessingException {
		
		System.out.println("here");
		System.out.println(json);
		
//		OrderDetailsWrapper odwrapper = objectMapper.readValue(json, OrderDetailsWrapper.class);
		OrderDetails[] ods = objectMapper.readValue(json, OrderDetails[].class);
		
		Customer customer = customerServiceImpl.getCustomerByEmail(principal.getName());
		int customerID = customer.getCustomerID();
		
		int currentOrderID = orderServiceImpl.createNewOrder(customerID);
		
		
		int countOfRecord=  orderDetailsServiceImpl.insertOrderDetails(ods,currentOrderID);
		
		service.sendOrderSuccessEmail(customer);
		
//		model.addAttribute("currentOrderID",currentRequestID);
//		model.addAttribute("orderdetails",/);
		
//		for(OrderDetails od:ods) {
//			System.out.println(od.getOrderdetailsID());
//			System.out.println(od.getOrderID());
//			System.out.println(od.getProductID());
//			System.out.println(od.getQuantity());
//			System.out.println(od.getProductprice());
//			System.out.println(od.getTotalprice());
//			
//		}
//		return "redirect:/c/customer";
		return "redirect:/c/successfulorder";
	}
	
	@GetMapping("/c/successfulorder")
	public String handleSuccessfulOrder(Model model,Principal principal) {
		 
		return "successfulorderpage";
	}

	@PostMapping("/c/customer/delete")
	public String handleDeleteOrder(@RequestParam int orderID,Model model,Principal principal) {
		
//		orderDetailsServiceImpl.deleteOrderDetailswithOrderIDbySetcancelTrue(orderID);
		
		orderServiceImpl.cancelOrderwithOrderID(orderID);
//					iscancelled => true;
//					
//		service.sendOrderDeletionEmail(,orderID);
		
		
		
		return "redirect:/c/customer";
		
		
	}
	
}
