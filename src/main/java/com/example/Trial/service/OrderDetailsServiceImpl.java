package com.example.Trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.model.OrderDetails;
import com.example.Trial.repository.OrderDetailsDao;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	
	@Autowired
	private OrderDetailsDao orderDetailsDaoImpl;
	
	@Override
	public int insertOrderDetails(OrderDetails[] ods,int currentOrderId) {
		// TODO Auto-generated method stub
		
		int countOfRecord = orderDetailsDaoImpl.insertOrderDetails(ods,currentOrderId);
		
		return countOfRecord;
	}

}
