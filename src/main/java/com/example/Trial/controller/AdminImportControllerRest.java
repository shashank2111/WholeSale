package com.example.Trial.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Trial.model.RequestDetails;
import com.example.Trial.model.RequestDetailsWrapper;

@Controller
public class AdminImportControllerRest {
	
//	@ResponseBody
//	@PostMapping(value="/confirmrequestdetails",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//	public String handleConfirmRequest2( RequestDetailsWrapper rqwrapper,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes,  Model model) throws IOException {
////		System.out.println(rqdetails.toString());
//		System.out.println(rqwrapper.toString());
//		System.out.println("here");
//		return "okay";  
//		
//	}
	
	@ResponseBody
	@PostMapping("/confirmrequestdetails")
	public String saveBooks(@ModelAttribute("requestdetailswrapper") RequestDetailsWrapper rqwrapper, Model model) {
//	    bookService.saveAll(form.getBooks());
		List<RequestDetails> rqs = rqwrapper.getDetails();
		for(RequestDetails rq :rqs) {
			System.out.println(rq.getProductID());
		}
//	    model.addAttribute("books", bookService.findAll());
	    return "okay";
	}
	
	
}
