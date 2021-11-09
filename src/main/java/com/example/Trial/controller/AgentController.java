package com.example.Trial.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Trial.model.Agent;
import com.example.Trial.model.OrderDetailsEditor;
import com.example.Trial.model.OrderDetailsWrapper;
import com.example.Trial.model.Request;
import com.example.Trial.model.RequestDetailsView;
import com.example.Trial.service.AgentService;
import com.example.Trial.service.EmailSenderService;
import com.example.Trial.service.ProductService;
import com.example.Trial.service.RequestDetailsService;
import com.example.Trial.service.RequestService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class AgentController {
		
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private AgentService agentServiceImpl;
	
	@Autowired
	private RequestService requestServiceImpl;

	@Autowired
	private RequestDetailsService requestDetailsServiceImpl;
	
	@Autowired
	private ProductService productServiceImpl;
	
	@Autowired
	private EmailSenderService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(OrderDetailsWrapper.class, new OrderDetailsEditor(objectMapper));
	}
	
	@GetMapping("/a/agent")
	public String handleAgentEntrance(Principal principal,Model model) {
		model.addAttribute("email", principal.getName());	
		
		Agent agent = agentServiceImpl.getAgentByEmail(principal.getName());
		
		int agentID = agent.getAgentID();
		
		System.out.println("agent Id =" + Integer.toString(agentID));
		
		List<Request> PendingRequeststome = requestServiceImpl.getPendingRequeststome(agentID);
		List<Request> unFulfilledRequestsbyme = requestServiceImpl.getunFulfilledRequestsbyme(agentID);
		
		model.addAttribute("agentID", agentID);
		model.addAttribute("PendingRequeststome", PendingRequeststome);
		model.addAttribute("unFulfilledRequestsbyme", unFulfilledRequestsbyme);
		
		for(Request rq:unFulfilledRequestsbyme) {
			System.out.println(rq.getRequestID());
			System.out.println(rq.isIsinprocess());
			System.out.println(rq.isIsfulfilled());
		}
		
		return "agentpage";
	}
	
	@GetMapping("/a/agent/allmyexports/{agentID}")
	public String handleSeeAllMyExports(@PathVariable("agentID") int agentID,Principal pricipal, Model model) {
		
		List<Request> allMyExports = requestServiceImpl.getAllMyExports(agentID);
		model.addAttribute("allMyExports", allMyExports);
		
		return "myexports";
	}
	
	@GetMapping("/a/agent/handlerequest/{requestID}")
	public String handleRequestProcessing(@PathVariable("requestID") int requestID , Model model,Principal principal) {
		
		int countOfRecord = requestServiceImpl.makeisinprocesstrue(requestID);
		
		return "redirect:/a/agent";
	}
	
	@GetMapping("/a/agent/showdetails/{requestID}")
	public String handleshowdetailsForRequest(@PathVariable("requestID") int requestID , Model model,Principal principal) {
		
		List<RequestDetailsView> allRequestswithRequestID = requestDetailsServiceImpl.getAllRequestDetailsViewwithRequestID(requestID);
		
		model.addAttribute("allRequestDetailswithRequestID", allRequestswithRequestID);
		for(RequestDetailsView rqview:allRequestswithRequestID) {
			System.out.println(rqview.getRequestID());
			System.out.println(rqview.getRequestdetailsID());
//			System.out.println(rqview.);
		}
		return "showrequestdetailspage";
	}
	
	
//	@GetMapping("/c/makeorder")
//	public String handleMakeOrder(Principal principal,Model model) {
//		
//		List<Product> allProducts = productServiceImpl.getAllProducts();
//		
//		model.addAttribute("allProducts",allProducts);
//		model.addAttribute("email",principal.getName());
//		return "makeorderpage.html";
//	}
//	
//	@GetMapping("/c/index")
//	public String tryJson(Model model) {
//		
//		return "index";
//	}
//	
//	
//	@PostMapping("/c/json")
//	public String handleJson(@RequestParam String json,Model model,Principal principal) throws JsonMappingException, JsonProcessingException {
//		
//		System.out.println("here");
//		System.out.println(json);
//		
////		OrderDetailsWrapper odwrapper = objectMapper.readValue(json, OrderDetailsWrapper.class);
//		OrderDetails[] ods = objectMapper.readValue(json, OrderDetails[].class);
//		
//		Customer customer = customerServiceImpl.getCustomerByEmail(principal.getName());
//		int customerID = customer.getCustomerID();
//		
//		int currentOrderID = orderServiceImpl.createNewOrder(customerID);
//		
//		
//		int countOfRecord=  orderDetailsServiceImpl.insertOrderDetails(ods,currentOrderID);
//		
//		service.sendOrderSuccessEmail(customer);
//		
////		model.addAttribute("currentOrderID",currentRequestID);
////		model.addAttribute("orderdetails",/);
//		
////		for(OrderDetails od:ods) {
////			System.out.println(od.getOrderdetailsID());
////			System.out.println(od.getOrderID());
////			System.out.println(od.getProductID());
////			System.out.println(od.getQuantity());
////			System.out.println(od.getProductprice());
////			System.out.println(od.getTotalprice());
////			
////		}
////		return "redirect:/c/customer";
//		return "redirect:/c/successfulorder";
//	}
//	
//	@GetMapping("/c/successfulorder")
//	public String handleSuccessfulOrder(Model model,Principal principal) {
//		 
//		return "successfulorderpage";
//	}
//
//	@PostMapping("/c/customer/delete")
//	public String handleDeleteOrder(@RequestParam int orderID,Model model,Principal principal) {
//		
////		orderDetailsServiceImpl.deleteOrderDetailswithOrderIDbySetcancelTrue(orderID);
//		
//		orderServiceImpl.cancelOrderwithOrderID(orderID);
////					iscancelled => true;
////					
////		service.sendOrderDeletionEmail(,orderID);
//		
//		
//		
//		return "redirect:/c/customer";
//		
//		
//	}
	
}
