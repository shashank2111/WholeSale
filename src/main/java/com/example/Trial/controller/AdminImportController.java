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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Trial.model.Agent;
import com.example.Trial.model.Request;
import com.example.Trial.model.RequestDetails;
import com.example.Trial.model.RequestDetailsView;
import com.example.Trial.model.SupplyView;
import com.example.Trial.service.AgentService;
import com.example.Trial.service.EmailSenderService;
import com.example.Trial.service.RequestDetailsService;
import com.example.Trial.service.RequestService;
import com.example.Trial.service.SupplyService;

	
@Controller
public class AdminImportController {
	
	@Autowired
	private RequestService requestServiceImpl;
	@Autowired
	private RequestDetailsService requestDetailsServiceImpl;
	@Autowired
	private AgentService agentServiceImpl;
	
	@Autowired
	private SupplyService supplyServiceImpl;
	
	@Autowired
	private EmailSenderService emailService;
	
	@GetMapping("/import")
	public String handleManageImport(Principal principal,Model model) {
//		model.addAttribute("message","You are in managecustomer");
		model.addAttribute("email",principal.getName());
//		return "importmanagement";
		
		List<Request> Requestsnotshipped = requestServiceImpl.getAllRequestsisinprocessfalse();
		
		List<Request> Requestsshippednotreceived = requestServiceImpl.getAllRequestsisinprocesstrueisfulfilledfalse();
		
		model.addAttribute("requestsnotshipped", Requestsnotshipped);
		model.addAttribute("requestsshippednotreceived", Requestsshippednotreceived);
		
		return "unfulfilledrequests";
	}
	
	@GetMapping("/import/allimports")
	public String handleAllImports(Principal principal,Model model) {
		
		List<Request> allImports = requestServiceImpl.getAllImports();
		model.addAttribute("allImports", allImports); 
		
		return "allimports";
		
	}
	
	@GetMapping("/import/showrequestdetails/{requestID}")
	public String handleShowRequestDetails(@PathVariable("requestID") int requestID,Principal principal,Model model) {
		
		List<RequestDetailsView> allRequestswithRequestID = requestDetailsServiceImpl.getAllRequestDetailsViewwithRequestID(requestID);
		model.addAttribute("allRequestDetailswithRequestID", allRequestswithRequestID);
		for(RequestDetailsView rqview:allRequestswithRequestID) {
			System.out.println(rqview.getRequestID());
			System.out.println(rqview.getRequestdetailsID());
//			System.out.println(rqview.);
		} 
		return "showrequestdetailspage";
		
	}
	
	@GetMapping("/import/receiverequest/{requestID}/{agentID}")
	public String handleReceiveImport(@PathVariable("requestID") int requestID ,@PathVariable("agentID") int agentID, Principal principal , Model model) {
		
		int countOfRecord = requestServiceImpl.receiveImportsetisfulfilledtrue(requestID);
		
		Agent agent = agentServiceImpl.getAgentByAgentID(agentID);
		
		emailService.sendImportReceivedSuccessEmail(agent,requestID);
		
		
		return "redirect:/import";
	}
	
	@GetMapping("/makerequest/selectagent")
	public String handleSelectAgent(Model model) {
		System.out.println("in handleSelectAgent getmapping");
		List<Agent> allagents = agentServiceImpl.getAllAgents();
		model.addAttribute("allagents",allagents);
		return "selectagent";
	}
	
	@PostMapping("/makerequest/selectagent")
	public String handleSelectAgent(RedirectAttributes redirectAttributes,Model model,@RequestParam String agentID ) {
			
		System.out.println("In handleselect Mapping post");
		int agentId = Integer.parseInt(agentID);
		redirectAttributes.addAttribute("agentID",agentId);
		System.out.println(agentId);
		return "redirect:/makerequest";
	}
	
	@GetMapping("/makerequest")
	public String handleMakeRequest(@RequestParam("agentID") String agentID, Model model) {
		System.out.println("IN handle make request Get");
		System.out.println(Integer.parseInt(agentID));
		int agentId = Integer.parseInt(agentID);
		System.out.println("above is the agentID ");
		System.out.println("here1");
		int currentRequestID = requestServiceImpl.createNewRequest(agentId);
		System.out.println("here2"); 

		model.addAttribute("currentRequestID",currentRequestID);
		model.addAttribute("rqdetails",new RequestDetails(currentRequestID));
		
//		List<Agent> allAgents = agentServiceImpl.getAllAgents();
//		model.addAttribute("allagents",allAgents);
		
		List<SupplyView> allProductsSuppliedByAgentID =  supplyServiceImpl.getAllProductssuppliedByAgentID(agentId);
		
		model.addAttribute("allProductsSuppliedByAgentID",allProductsSuppliedByAgentID);
		return "makerequestpage";	
	}
	
	@PostMapping("/makerequest")
	public String handleAddButton(@ModelAttribute("rqdetails") RequestDetails rqdetails ,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
		

		System.out.println(rqdetails.getRequestID());
		
		
		int countOfRecord = requestDetailsServiceImpl.addRequestDetail(rqdetails);
		List<RequestDetails> requestsMade =  requestDetailsServiceImpl.findAllDetailswithRequestID(rqdetails.getRequestID());
		
		int currentRequestID = rqdetails.getRequestID();
		int agentId = requestServiceImpl.getAgentUsingRequestId(currentRequestID);
		model.addAttribute("rqdetails",new RequestDetails(rqdetails.getRequestID()));
		model.addAttribute("currentRequestID",rqdetails.getRequestID());
		model.addAttribute("requestsMade",requestsMade);
//		
//		List<Agent> allAgents = agentServiceImpl.getAllAgents();
//		model.addAttribute("allagents",allAgents);
		
		List<SupplyView> allProductsSuppliedByAgentID =  supplyServiceImpl.getAllProductssuppliedByAgentID(agentId);
		model.addAttribute("allProductsSuppliedByAgentID",allProductsSuppliedByAgentID);
		
		return "makerequestPage";
		
	}
	
	@PostMapping("/requestdetail/delete") 
	public String handleDeleteButton(@ModelAttribute("detailtodelete") RequestDetails rqdetail,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
		
		int currentRequestID = rqdetail.getRequestID();
		int countOfRecord = requestDetailsServiceImpl.deleteDetail(rqdetail);
		
		List<RequestDetails> requestsMade =  requestDetailsServiceImpl.findAllDetailswithRequestID(currentRequestID);
		
		int agentId = requestServiceImpl.getAgentUsingRequestId(currentRequestID);
		
		model.addAttribute("rqdetails",new RequestDetails(currentRequestID));
		model.addAttribute("currentRequestID",currentRequestID);
		model.addAttribute("requestsMade",requestsMade);
		
		List<SupplyView> allProductsSuppliedByAgentID =  supplyServiceImpl.getAllProductssuppliedByAgentID(agentId);
		model.addAttribute("allProductsSuppliedByAgentID",allProductsSuppliedByAgentID);
		
		return "makerequestPage";
		
	}
	
	@PostMapping("/confirmrequest")
	public String handleConfirmRequest(Model model,@RequestParam String currentRequestID) {
		
		int currentrequestID = Integer.parseInt(currentRequestID);
		int totalamount = requestDetailsServiceImpl.getTotalAmountwithRequestID(currentrequestID);
		
		int countOfRecord = requestServiceImpl.setRequestTotalamount(currentrequestID,totalamount);
		
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
