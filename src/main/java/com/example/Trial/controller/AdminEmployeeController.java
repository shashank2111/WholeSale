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

import com.example.Trial.model.Employee;
import com.example.Trial.service.EmployeeService;

@Controller
public class AdminEmployeeController {
	@Autowired
	private EmployeeService employeeServiceImpl;
	
//	@GetMapping("/employee")
//	public String handleEmployees(Model model) {
//		                   
//		model.addAttribute("message" ,"Hello Shashank");
//		return "employeemanagement";
//	}
	
	@GetMapping("/employee")
	public String handleManageCustomer(Principal principal,Model model) {
		model.addAttribute("message","You are in managecustomer");
		model.addAttribute("email",principal.getName());
//		System.out.println(redirectAttributes.getAttribute("customers"));
//		model.addAttribute("customers",redirectAttributes.getAttribute("customers"));
//		System.out.println(model.getAttribute("customers"));
//		model.addAttribute("customers",customers);
		return "employeemanagement";
	}
	
	@PostMapping("/employee")
	public String handleAddEmployee(@ModelAttribute("employee") Employee employee ,Model model) {
		
		int employeeID = employeeServiceImpl.addEmployee(employee);
		System.out.println("added the customer");
//		model.addAttribute("successmessage","The customer has been registered successfully");
		model.addAttribute("success",true);
		model.addAttribute("employeeID",employeeID);
		return "employeemanagement";
		
	}	
	
	@PostMapping("/employee/search")
	public String handleSearchEmployee(@ModelAttribute("employee")  Employee employee,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes,Model model) {
		
		 
		System.out.println(employee.getEmail());
		System.out.println(employee.getEmployeeID());
		List<Employee> employees = employeeServiceImpl.findAllEmployeesByEmployee(employee);
		
		redirectAttributes.addFlashAttribute("employees",employees);
//		System.out.println(redirectAttributes.getAttribute("employees"));
		return "redirect:/employee"; 
	}
	
//	@GetMapping("/customer/edit")
//	public String handleEditCustomer(@ModelAttribute("Customer") Customer customer,BindingResult bidingResult,Model model,Principal principal) {
//		
//		model.addAttribute("useremail",principal.getName()); 
//		model.addAttribute("customer",customer);	
//		return "customeredit";
//	}
//	
	@PostMapping("/employee/edit")
	public String handleCurrentEmployee(@ModelAttribute("employeecurrent") Employee employee,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model,Principal principal) {
		System.out.println(employee.toString());
		model.addAttribute("useremail",principal.getName()); 
		model.addAttribute("originalemployee",employee);
		System.out.println(employee.toString());
		return "employeeedit";
	}
	
	@PostMapping("/employee/edit/confirm")
	public String handleEditEmployeeData(@ModelAttribute("employee") Employee employee,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {
		
		int count = employeeServiceImpl.updateEmployee(employee);
		System.out.println("No of rows affected = " + Integer.toString(count));
		System.out.println("Updated the employee successfully");
		System.out.println(employee.toString());
		return "redirect:/employee"; 	
	}
	
	@PostMapping("/employee/delete")
	public String handleDeleteEmployee(@ModelAttribute("customertodelete") Employee employee,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model) {
		
		int countOfRecord = employeeServiceImpl.deleteEmployee(employee);
		System.out.println("Employee successfully deleted");
		return "redirect:/employee";
	}
	
	@GetMapping("/employee/allemployees")
	public String handleShowAllEmployees(Model model,Principal principal) {
		
		List<Employee> allEmployees = employeeServiceImpl.getAllEmployees();
		for(Employee em:allEmployees) {
			System.out.println(em.getEmail());
		}
		model.addAttribute("allEmployees", allEmployees);
		return "allEmployees";
		
	}
}
