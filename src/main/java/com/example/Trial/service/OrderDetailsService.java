package com.example.Trial.service;

import com.example.Trial.model.OrderDetails;

public interface OrderDetailsService {
	public int insertOrderDetails(OrderDetails[] ods,int currentOrderID);
}
