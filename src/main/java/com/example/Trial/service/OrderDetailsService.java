package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.OrderDetails;
import com.example.Trial.model.OrderDetailsView;

public interface OrderDetailsService {
	public int insertOrderDetails(OrderDetails[] ods,int currentOrderID);
	public List<OrderDetailsView> getOrderDetailsViewbyOrderID(int orderID);
	
}
