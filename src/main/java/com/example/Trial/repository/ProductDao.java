package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.Category;
import com.example.Trial.model.OrderDetailsView;
import com.example.Trial.model.Product;

public interface ProductDao {

	public List<Product> getAllProducts();
	public List<Product> getAllProductswithCategoryID(int categoryID);
	public int addProduct(Product product);
	public int addCategory(Category category);
	public List<Product> findAllProductsbyProduct(Product product);
	public Product getProductfromProductID(int productID);
	public int deleteProductwithProductID(int productID);
	public int updateProduct(Product product);
	public int updateQuantityProduct(List<OrderDetailsView> allOrderDetailsViews);
	public List<Product> getAllAlertProducts();
}
