package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.Category;

public interface StoreDao {
	public List<Category> getAllCategories();
	public String getCategoryNamefromID(int categoryID);
}
