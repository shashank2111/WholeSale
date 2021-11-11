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

import com.example.Trial.model.Agent;
import com.example.Trial.service.AgentService;
import com.example.Trial.service.EmailSenderService;

@Controller
public class AdminAgentController {
	
	@Autowired
	private AgentService agentServiceImpl;
	
	@Autowired
	private EmailSenderService emailService;
	
//	@GetMapping("/agent")
//	public String handleAgents(Model model) {
//		                   
//		model.addAttribute("message" ,"Hello Shashank");
//		return "agentmanagement";
//	}
	
	@GetMapping("/agent")
	public String handleManageAgent(Principal principal,Model model) {
		model.addAttribute("message","You are in managecustomer");
		model.addAttribute("email",principal.getName());
//		System.out.println(redirectAttributes.getAttribute("customers"));
//		model.addAttribute("customers",redirectAttributes.getAttribute("customers"));
//		System.out.println(model.getAttribute("customers"));
//		model.addAttribute("customers",customers);
		return "agentmanagement";
	}
	
	@PostMapping("/agent")
	public String handleAddAgent(@ModelAttribute("agent") Agent agent ,Model model) {
		
		int agentID = agentServiceImpl.addAgent(agent);
		System.out.println("added the agent");
//		model.addAttribute("successmessage","The customer has been registered successfully");
		model.addAttribute("success",true);
		model.addAttribute("agentID",agentID);
		
		emailService.sendEmail(agent);
		
		return "agentmanagement";
		
	}	
	
	@PostMapping("/agent/search")
	public String handleSearchAgent(@ModelAttribute("agent")  Agent agent,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes,Model model) {
		
		 
		System.out.println(agent.getEmail());
		System.out.println(agent.getAgentID());
		List<Agent> agents = agentServiceImpl.findAllAgentsByAgent(agent);
		
		redirectAttributes.addFlashAttribute("agents",agents);
//		System.out.println(redirectAttributes.getAttribute("agents"));
		return "redirect:/agent"; 
	}
	
//	@GetMapping("/customer/edit")
//	public String handleEditCustomer(@ModelAttribute("Customer") Customer customer,BindingResult bidingResult,Model model,Principal principal) {
//		
//		model.addAttribute("useremail",principal.getName()); 
//		model.addAttribute("customer",customer);	
//		return "customeredit";
//	}
//	
	@PostMapping("/agent/edit")
	public String handleCurrentAgent(@ModelAttribute("agentcurrent") Agent agent,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model,Principal principal) {
		System.out.println(agent.toString());
		model.addAttribute("useremail",principal.getName()); 
		model.addAttribute("originalagent",agent);
		System.out.println(agent.toString());
		
		
		return "agentedit";
	}
	
	@PostMapping("/agent/edit/confirm")
	public String handleEditAgentData(@ModelAttribute("agent") Agent agent,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {
		
		int count = agentServiceImpl.updateAgent(agent);
		System.out.println("No of rows affected = " + Integer.toString(count));
		System.out.println("Updated the agent successfully");
		System.out.println(agent.toString());
		emailService.sendAgentDataUpdateSuccessEmail(agent);
		return "redirect:/agent"; 	
	}
	
	@PostMapping("/agent/delete")
	public String handleDeleteAgent(@ModelAttribute("customertodelete") Agent agent,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {
		
		int countOfRecord = agentServiceImpl.deleteAgent(agent);
		System.out.println("Agent successfully deleted");
		
		emailService.sendAgentUnsubscribedSuccessEmail(agent);
		
		return "redirect:/agent";
	}
	
	@GetMapping("/agent/allagents")
	public String handleAllAgents(Model model,Principal principal) {
		
		List<Agent> allAgents = agentServiceImpl.getAllAgents();
		
		model.addAttribute("allAgents", allAgents);
		return "allagents";
		
	}
}
