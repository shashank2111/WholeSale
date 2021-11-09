package com.example.Trial.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Trial.model.OrderDetailsView;
import com.example.Trial.model.OrdersView;
import com.example.Trial.service.OrderDetailsService;
import com.example.Trial.service.OrderService;

@Controller
public class AdminOrderController {

	@Autowired
	private OrderService orderServiceImpl;
	
	@Autowired
	private OrderDetailsService orderDetailsServiceImpl;
	
	@GetMapping("/order")
	public String handleOrdersPage(Model model,Principal principal) {
		
		List<OrdersView> unAttendedOrders = orderServiceImpl.getAllUnAttendedOrders();
//		for(OrdersView ov:unAttendedOrders) {
//			System.out.println(ov.getCustomeremail());
//		}
		model.addAttribute("email", principal.getName());
		model.addAttribute("unAttendedOrders",unAttendedOrders);
		
		List<OrdersView> unReceivedOrders = orderServiceImpl.getAllUnReceivedOrders();
		for(OrdersView ov:unReceivedOrders) {
			System.out.println(ov.isOrderIsPending());
			System.out.println(ov.isIshandled());
		}
		
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
	
	@GetMapping("/order/makependingfalse/{orderID}")
	public String handleMakePendingOrderfalse(@PathVariable("orderID") int orderID,Model model) {
		
		int countOfRecord = orderServiceImpl.makePendingOrderFalsewithOrderID(orderID);
		return "redirect:/order";
	}
	
	@GetMapping("/order/handleorder/{orderID}")
	public String handleOrderHandling(@PathVariable("orderID") int orderID,Model model) {
		
		int countOfRecord = orderServiceImpl.handleOrderwithOrderID(orderID);
//		System.out.println(orderID);
		return "redirect:/order";
	}
	
}
