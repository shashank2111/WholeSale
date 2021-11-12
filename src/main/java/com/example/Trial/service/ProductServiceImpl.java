package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.model.Category;
import com.example.Trial.model.Product;
import com.example.Trial.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDaoImpl;
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> allProducts = productDaoImpl.getAllProducts();
		
		return allProducts;
	}

	@Override
	public List<Product> getAllProductswithCategoryID(int categoryID) {
		// TODO Auto-generated method stub
		List<Product> allProductswithCategoryID = productDaoImpl.getAllProductswithCategoryID(categoryID);
		return allProductswithCategoryID;
	}

	@Override
	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		int productID = productDaoImpl.addProduct(product);
		return productID;
	}

	@Override
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		
		int countOfRecord = productDaoImpl.addCategory(category);
		
		return countOfRecord;
	}

	@Override
	public List<Product> findAllProductsbyProduct(Product product) {
		// TODO Auto-generated method stub
		
		List<Product> allProducts= productDaoImpl.findAllProductsbyProduct(product);
		return allProducts;
	}

}
