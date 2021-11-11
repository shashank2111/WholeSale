package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.Category;

public interface StoreService {
	
	public List<Category> getAllCategories();
	public String getCategoryNamefromID(int categoryID);
	
}
