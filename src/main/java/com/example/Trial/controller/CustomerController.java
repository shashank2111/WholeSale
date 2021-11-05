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
import com.example.Trial.model.OrderDetails;
import com.example.Trial.model.OrderDetailsEditor;
import com.example.Trial.model.OrderDetailsWrapper;
import com.example.Trial.model.Product;
import com.example.Trial.service.CustomerService;
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(OrderDetailsWrapper.class, new OrderDetailsEditor(objectMapper));
	}
	
	@GetMapping("/c/customer")
	public String handleCustomerEntrance(Principal principal,Model model) {
		model.addAttribute("email", principal.getName());	
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
	
	
}
