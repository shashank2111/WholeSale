package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.model.Category;
import com.example.Trial.repository.StoreDao;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreDao storeDaoImpl;
	
	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		
		List<Category> allCategories =  storeDaoImpl.getAllCategories();
		
		return allCategories;
	}

	@Override
	public String getCategoryNamefromID(int categoryID) {
		// TODO Auto-generated method stub
		String categorytype = storeDaoImpl.getCategoryNamefromID(categoryID);
		return categorytype;
	}

}
