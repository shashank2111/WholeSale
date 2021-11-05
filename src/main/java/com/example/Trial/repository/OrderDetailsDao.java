package com.example.Trial.repository;

import com.example.Trial.model.OrderDetails;

public interface OrderDetailsDao {
	public int insertOrderDetails(OrderDetails[] ods,int currentOrderID);
}
