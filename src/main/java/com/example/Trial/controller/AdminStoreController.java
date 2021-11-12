package com.example.Trial.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Trial.model.Agent;
import com.example.Trial.model.AgentIDEditor;
import com.example.Trial.model.AgentIDWrapper;
import com.example.Trial.model.Category;
import com.example.Trial.model.Employee;
import com.example.Trial.model.Product;
import com.example.Trial.service.AgentService;
import com.example.Trial.service.ProductService;
import com.example.Trial.service.StoreService;
import com.example.Trial.service.SupplyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
  

@Controller
public class AdminStoreController {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(AgentIDWrapper.class, new AgentIDEditor(objectMapper));
	}
	
	@Autowired
	private AgentService agentServiceImpl;
	
	@Autowired
	private StoreService storeServiceImpl;
	
	@Autowired
	private SupplyService supplyServiceImpl;
	
	@Autowired
	private ProductService productServiceImpl;
	
	@GetMapping("/store")
	public String handleStore(Principal principal, Model model) {
		
		List<Category> allCategories =  storeServiceImpl.getAllCategories();
		 
		model.addAttribute("allCategories", allCategories);
		List<Product> allProducts = productServiceImpl.getAllProducts();
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("toshow",false);
		model.addAttribute("email", principal.getName());
		return "storemanagement";
	}
	
	@PostMapping("/store/search")
	public String handleSearchEmployee(@ModelAttribute("product")  Product product,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes,Model model) {
		
		 
		System.out.println(product.getProductID());
		System.out.println(product.getProductname());
		List<Product> matchedproducts = productServiceImpl.findAllProductsbyProduct(product);
		
		redirectAttributes.addFlashAttribute("matchedproducts",matchedproducts);
//		System.out.println(redirectAttributes.getAttribute("employees"));
		return "redirect:/store/addproduct"; 
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
	
	@PostMapping("/store/addproduct")
	public String handleAddProduct(@ModelAttribute("product") Product product,BindingResult bindingResult,RedirectAttributes redirectAttributes,Principal principal, Model model) {
		
		int productID = productServiceImpl.addProduct(product);
		redirectAttributes.addAttribute("productID", productID);
		return "redirect:/store/productsupply";
	}
	

	@GetMapping("/store/productsupply")
	public String handleAddProductsupply(@RequestParam("productID") int productID,RedirectAttributes redirectAttributes,Principal principal, Model model) {
		
		List<Agent> allAgents = agentServiceImpl.getAllAgents();
		model.addAttribute("allAgents", allAgents);
		model.addAttribute("productID",productID);
		System.out.println(productID);
		redirectAttributes.addAttribute("productID", productID);
		return "mapproductagent";
	}
	
	@ResponseBody
	@PostMapping("/store/productsupply")
	public String handleAddProductsupplyPOST(@RequestParam("json") String json,@RequestParam("productID") int productID,Principal principal, Model model) throws JsonMappingException,JsonProcessingException {
		
		
//		List<String> liststring;
		
//		OrderDetails[] ods = objectMapper.readValue(json, OrderDetails[].class);
		
		System.out.println(json);
		System.out.println(productID);
		Agent[] agents = objectMapper.readValue(json, Agent[].class);
		
//		liststring = objectMapper.readValue(json, new TypeReference());
		
//		JSONArray array = JSONArray(json);
//		array.
		for(int i=0; i < (int)agents.length ; i++) {
//			Object row = agentIDs[i];
			System.out.println(agents[i].getAgentID());
		}
		
		
		
		supplyServiceImpl.addAgentsforProduct(productID,agents);
		
		
//		JSONObject jsnobject = (JSONObject) JSONValue.parse(json);  
//		System.out.println(jsnobject);
//		JSONArray jsonArray = jsnobject	
//		Integer[] agents = objectMapper.readValue(json, Integer[].class);
//		for(int a:agents) {
//			System.out.println(a);
//		}
		return "okay";
	}
	
	
	@GetMapping("/store/addcategory")
	public String handleAddCategory(Principal principal, Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		model.addAttribute("email", principal.getName());
		System.out.println(principal.getName());
		return "addcategoryform";
	}
	
	@PostMapping("/store/addcategory")
	public String handleAddCategory(@ModelAttribute("category") Category category,Principal principal,Model model) {
		
		int countOfRecord = productServiceImpl.addCategory(category);
		
		return "redirect:/store";
	}

	@PostMapping("/store/product/edit")
	public String handleCurrentEmployee(@ModelAttribute("productcurrent") Product product,BindingResult bindingResult,HttpServletRequest request,RedirectAttributes redirectAttributes,Model model,Principal principal) {
//		System.out.println(employee.toString());
		model.addAttribute("useremail",principal.getName()); 
		model.addAttribute("originalproduct",product);
		System.out.println(product.toString());
		return "productedit";
	}
	
	
	
}
