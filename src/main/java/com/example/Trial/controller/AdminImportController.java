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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Trial.model.Agent;
import com.example.Trial.model.RequestDetails;
import com.example.Trial.service.AgentService;
import com.example.Trial.service.RequestDetailsService;
import com.example.Trial.service.RequestService;

	
@Controller
public class AdminImportController {
	
	@Autowired
	private RequestService requestServiceImpl;
	@Autowired
	private RequestDetailsService requestDetailsServiceImpl;
	@Autowired
	private AgentService agentServiceImpl;
	
	@GetMapping("/import")
	public String handleManageImport(Principal principal,Model model) {
//		model.addAttribute("message","You are in managecustomer");
//		model.addAttribute("email",principal.getName());
//		return "importmanagement";
		return "unfulfilledrequests";
	}
	
	@GetMapping("/makerequest")
	public String handleMakeRequest(Model model) {
		
//		int maxReqeustId = importServiceImpl.getMaxRequestId();
//		int currentRequestID = maxReqeustId + 1;
//		int countofRecord = importServiceImpl.createNewRequestWithRequestID(currentRequestID);
		System.out.println("here1");
		int currentRequestID = requestServiceImpl.createNewRequest();
		System.out.println("here2");
//		int currentRequestID = 1;
		model.addAttribute("currentRequestID",currentRequestID);
		model.addAttribute("rqdetails",new RequestDetails(currentRequestID));
		
		List<Agent> allAgents = agentServiceImpl.getAllAgents();
		model.addAttribute("allagents",allAgents);
		return "makerequestpage";	
	}
	
	@PostMapping("/makerequest")
	public String handleAddButton(@ModelAttribute("rqdetails") RequestDetails rqdetails ,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
		
//		rqdetails.setRequestID(cur)
		System.out.println(rqdetails.getRequestID());
//		System.out.println();
		int countOfRecord = requestDetailsServiceImpl.addRequestDetail(rqdetails);
		List<RequestDetails> requestsMade =  requestDetailsServiceImpl.findAllDetailswithRequestID(rqdetails.getRequestID());
		
		model.addAttribute("rqdetails",new RequestDetails(rqdetails.getRequestID()));
		model.addAttribute("currentRequestID",rqdetails.getRequestID());
		model.addAttribute("requestsMade",requestsMade);
		
		List<Agent> allAgents = agentServiceImpl.getAllAgents();
		model.addAttribute("allagents",allAgents);
		
		return "makerequestPage";
		
	}
	
	@PostMapping("/requestdetail/delete") 
	public String handleDeleteButton(@ModelAttribute("detailtodelete") RequestDetails rqdetail,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
		
		int currentRequestID = rqdetail.getRequestID();
		int countOfRecord = requestDetailsServiceImpl.deleteDetail(rqdetail);
		
		List<RequestDetails> requestsMade =  requestDetailsServiceImpl.findAllDetailswithRequestID(currentRequestID);
		
		model.addAttribute("rqdetails",new RequestDetails(currentRequestID));
		model.addAttribute("currentRequestID",currentRequestID);
		model.addAttribute("requestsMade",requestsMade);
		
		return "makerequestPage";
		
	}
	
	@PostMapping("/confirmrequest")
	public String handleConfirmRequest(Model model,@RequestParam String currentRequestID) {
		
		int currentrequestID = Integer.parseInt(currentRequestID);
		List<RequestDetails> allrequestdetails = requestDetailsServiceImpl.findAllDetailswithRequestID(currentrequestID);
		
		model.addAttribute("allrequestdetails",allrequestdetails);
		
		return "successrequestpage";
		
	}
	@PostMapping("/cancelrequest")
	public String handleCancelRequest(Model model,@RequestParam String currentRequestID) {
		int currentrequestID = Integer.parseInt(currentRequestID);
		int countOfRecord = requestDetailsServiceImpl.deleteAllDetailswithRequestID(currentrequestID);
		int countofRecord = requestServiceImpl.deleteRequest(currentrequestID);
		return "redirect:/";
	}
	
//	@ResponseBody
//	@RequestMapping(value="/confirmrequestdetails",method=RequestMethod.POST)
//	public String handleConfirmRequest(@ModelAttribute("requestdetails") RequestDetails rqdetails,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes,  Model model) throws IOException {
//		System.out.println(rqdetails.toString());
//		System.out.println("here");
//		return "okay";  
//		
//	}
	
	
	
//	@PostMapping("/import")
//	public String handleAddImport(@ModelAttribute("import") Import import ,Model model) {
//		
//		int importID = importServiceImpl.addImport(import);
//		System.out.println("added the import");
//		model.addAttribute("success",true);
//		model.addAttribute("importID",importID);
//		return "importmanagement";
//		
//	}	
//	
//	@PostMapping("/import/search")
//	public String handleSearchImport(@ModelAttribute("import")  Import import,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes,Model model) {
//		
//		 
//		System.out.println(import.getEmail());
//		System.out.println(import.getImportID());
//		List<Import> imports = importServiceImpl.findAllImportsByImport(import);
//		
//		redirectAttributes.addFlashAttribute("imports",imports);
////		System.out.println(redirectAttributes.getAttribute("imports"));
//		return "redirect:/import"; 
//	}
//	
////	@GetMapping("/customer/edit")
////	public String handleEditCustomer(@ModelAttribute("Customer") Customer customer,BindingResult bidingResult,Model model,Principal principal) {
////		
////		model.addAttribute("useremail",principal.getName()); 
////		model.addAttribute("customer",customer);	
////		return "customeredit";
////	}
////	
//	@PostMapping("/import/edit")
//	public String handleCurrentImport(@ModelAttribute("importcurrent") Import import,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model,Principal principal) {
//		System.out.println(import.toString());
//		model.addAttribute("useremail",principal.getName()); 
//		model.addAttribute("originalimport",import);
//		System.out.println(import.toString());
//		return "importedit";
//	}
//	
//	@PostMapping("/import/edit/confirm")
//	public String handleEditImportData(@ModelAttribute("import") Import import,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {
//		
//		int count = importServiceImpl.updateImport(import);
//		System.out.println("No of rows affected = " + Integer.toString(count));
//		System.out.println("Updated the import successfully");
//		System.out.println(import.toString());
//		return "redirect:/import"; 	
//	}
//	
//	@PostMapping("/import/delete")
//	public String handleDeleteImport(@ModelAttribute("customertodelete") Import import,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {
//		
//		int countOfRecord = importServiceImpl.deleteImport(import);
//		System.out.println("Import successfully deleted");
//		return "redirect:/import";
//	}
	
}
