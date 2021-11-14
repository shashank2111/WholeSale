package com.example.Trial.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Trial.model.OrderDetails;
import com.example.Trial.model.OrderDetailsView;
import com.example.Trial.model.OrdersView;
import com.example.Trial.service.EmailSenderService;
import com.example.Trial.service.OrderDetailsService;
import com.example.Trial.service.OrderService;
import com.example.Trial.service.ProductService;

@Controller
public class AdminOrderController {

	@Autowired
	private OrderService orderServiceImpl;
	
	@Autowired
	private ProductService productServiceImpl;
	
	@Autowired
	private OrderDetailsService orderDetailsServiceImpl;
	
	@Autowired 
	private EmailSenderService emailService;
	
	@GetMapping("/order")
	public String handleOrdersPage(Model model,Principal principal) {
		
		List<OrdersView> unAttendedOrders = orderServiceImpl.getAllUnAttendedOrders();
		System.out.println("START \n");
		for(OrdersView ov:unAttendedOrders) {
			System.out.println(ov.getCustomeremail());
		}
		model.addAttribute("email", principal.getName());
		model.addAttribute("unAttendedOrders",unAttendedOrders);
		
		List<OrdersView> unReceivedOrders = orderServiceImpl.getAllUnReceivedOrders();
		for(OrdersView ov:unReceivedOrders) {
			System.out.println(ov.isOrderIsPending());
			System.out.println(ov.isIshandled());
		}
		System.out.println("END \n");
		model.addAttribute("unReceivedOrders",unReceivedOrders);
		return "unprocessedorders";
	}
	
//	@ResponseBody
	@PostMapping("/order/showorderdetails")
	public String handleSelectedOrder(@RequestParam int orderID ,Model model) {
		
		
		List<OrderDetailsView> allOrdersWithOrderID =  orderDetailsServiceImpl.getOrderDetailsViewbyOrderID(orderID);
//		orderID, orderDetailsID, productID, productname, quantity, productprice, totalamount, 
		model.addAttribute("allorderswithorderID", allOrdersWithOrderID);
		return "orderdetails";
	}
	
	@GetMapping("/order/makependingfalse/{orderID}/{customeremail}")
	public String handleMakePendingOrderfalse(@PathVariable("orderID") int orderID,@PathVariable("customeremail") String email,Model model) {
		
		int countOfRecord = orderServiceImpl.makePendingOrderFalsewithOrderID(orderID);
		emailService.sendOrderReceivedEmail(email,orderID);
		return "redirect:/order";
	}
	
	@Transactional
	@GetMapping("/order/handleorder/{orderID}/{customeremail}")
	public String handleOrderHandling(@PathVariable("orderID") int orderID,@PathVariable("customeremail") String email,Model model) {
		
		int countOfRecord = orderServiceImpl.handleOrderwithOrderID(orderID);
		
		List<OrderDetailsView> allOrderDetailsViews = orderDetailsServiceImpl.getOrderDetailsViewbyOrderID(orderID);
		int countOfRecord1 = productServiceImpl.updateQuantityProduct(allOrderDetailsViews);
		
//		System.out.println(orderID);
		emailService.sendOrderShippedEmail(email,orderID);
		return "redirect:/order";
	}
	
}
