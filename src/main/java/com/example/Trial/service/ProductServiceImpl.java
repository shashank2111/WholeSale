package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
