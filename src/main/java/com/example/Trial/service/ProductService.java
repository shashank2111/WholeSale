package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	public List<Product> getAllProductswithCategoryID(int categoryID);
	
}
