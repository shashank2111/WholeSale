package com.example.Trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.repository.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDaoImpl;
	
	@Override
	public int createNewOrder(int customerID) {
		// TODO Auto-generated method stub
		
		int currentOrderID = orderDaoImpl.createNewOrder(customerID);
		
		return currentOrderID;
	}

}
