package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.OrderDetails;
import com.example.Trial.model.OrderDetailsView;

public interface OrderDetailsDao {
	public int insertOrderDetails(OrderDetails[] ods,int currentOrderID);
	public List<OrderDetailsView> getOrderDetailsViewbyOrderID(int orderID);
}
