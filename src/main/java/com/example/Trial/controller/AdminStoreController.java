package com.example.Trial.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Trial.model.Category;
import com.example.Trial.model.Product;
import com.example.Trial.service.ProductService;
import com.example.Trial.service.StoreService;

@Controller
public class AdminStoreController {
	
	@Autowired
	private StoreService storeServiceImpl;
	
	@Autowired
	private ProductService productServiceImpl;
	
	@GetMapping("/store")
	public String handleStore(Principal principal, Model model) {
		
		List<Category> allCategories =  storeServiceImpl.getAllCategories();
		
		model.addAttribute("allCategories", allCategories);
		List<Product> allProducts = productServiceImpl.getAllProducts();
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("toshow",false);
		
		return "storemanagement";
	}
	
	@GetMapping("/store/showcategoryproducts/{categoryID}")
	public String handleShowCategoryProducts(@PathVariable("categoryID") int categoryID,Principal principal, Model model) {
		
		String categorytype = storeServiceImpl.getCategoryNamefromID(categoryID);
		
		List<Category> allCategories =  storeServiceImpl.getAllCategories();
		model.addAttribute("allCategories", allCategories);
		List<Product> allProductswithCategoryID = productServiceImpl.getAllProductswithCategoryID(categoryID);
		model.addAttribute("allProductswithCategoryID", allProductswithCategoryID);
		model.addAttribute("toshow", true);
		model.addAttribute("categorytype", categorytype);
		return "storemanagement";
	}
	
	@GetMapping("/store/addproduct")
	public String handleShowAllProducts(Principal principal, Model model) {
		
		List<Category> allCategories =  storeServiceImpl.getAllCategories();
		model.addAttribute("allCategories", allCategories);
		
		return "addproductform";
	}
	
	
	
}
