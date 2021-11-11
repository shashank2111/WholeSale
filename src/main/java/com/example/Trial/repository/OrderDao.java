package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.Order;
import com.example.Trial.model.OrdersView;

public interface OrderDao {
	
	public int createNewOrder(int customerID);
	public List<Order> getMyPendingOrders(int customerID);
	public List<Order> getMyFulfilledOrders(int customerID);
	public List<OrdersView> getAllUnAttendedOrders();
	public int cancelOrderwithOrderID(int orderID);
	public int handleOrderwithOrderID(int orderID);
	
	public List<OrdersView> getAllUnReceivedOrders();
	public int makePendingOrderFalsewithOrderID(int orderID);
	public int setTotalamountOfOrderID(int currentOrderID,int totalamount);
	
}
