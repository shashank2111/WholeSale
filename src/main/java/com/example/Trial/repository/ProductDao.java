package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.Category;
import com.example.Trial.model.Product;

public interface ProductDao {

	public List<Product> getAllProducts();
	public List<Product> getAllProductswithCategoryID(int categoryID);
	public int addProduct(Product product);
	public int addCategory(Category category);
	public List<Product> findAllProductsbyProduct(Product product);
}
