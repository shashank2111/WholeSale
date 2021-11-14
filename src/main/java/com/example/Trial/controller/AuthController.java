package com.example.Trial.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Trial.model.Product;
import com.example.Trial.model.User;
import com.example.Trial.service.ProductService;
import com.example.Trial.service.UserDetailsServiceImpl;

@Controller
public class AuthController {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private ProductService productServiceImpl;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 @GetMapping("/register")
	 public String register(Model model) {
		 model.addAttribute("user",new User());
		 return "register";
	 }

	 @GetMapping("/login")
	 public String login(Model model){
	    model.addAttribute("user",new User());
	    return "login";  
	 }
	 
	 @PostMapping("/register")
	 public String handlePOSTregister(@ModelAttribute("user") User user) {
		 System.out.println(user.toString());
		 System.out.println(user.getEmail());
		 System.out.println(user.getPassword());
		 System.out.println(user.getRoletitle()); 
		 user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		 user.setRoletitle(user.getRoletitle());
		 userDetailsServiceImpl.saveUser(user);
		 return "redirect:/login";
	 }
	 
	 @GetMapping("/")
	 public String handleRoot(Principal principal,Model model) {
		 model.addAttribute("email",principal.getName());
		 
		 List<Product> alertProducts = productServiceImpl.getAllAlertProducts();
		 for(Product p:alertProducts) {
			 System.out.println(p.getProductname());
		 }
		 model.addAttribute("alertProducts", alertProducts);
		 return "root.html";
	 }
}
