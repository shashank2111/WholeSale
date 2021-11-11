package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.model.Order;
import com.example.Trial.model.OrdersView;
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

	@Override
	public List<Order> getMyPendingOrders(int customerID) {
		// TODO Auto-generated method stub
		
		List<Order> myPendingOrders = orderDaoImpl.getMyPendingOrders(customerID);
		
		return myPendingOrders;
	}

	@Override
	public List<Order> getMyFulfilledOrders(int customerID) {
		// TODO Auto-generated method stub
		List<Order> myFulfilledOrders = orderDaoImpl.getMyFulfilledOrders(customerID);
		return myFulfilledOrders;
	}

	@Override
	public List<OrdersView> getAllUnAttendedOrders() {
		// TODO Auto-generated method stub
		List<OrdersView> allUnAttendedOrders = orderDaoImpl.getAllUnAttendedOrders();
		return allUnAttendedOrders;
	}

	@Override
	public int cancelOrderwithOrderID(int orderID) {
		// TODO Auto-generated method stub
		
		int countOfRecord = orderDaoImpl.cancelOrderwithOrderID(orderID);
		return countOfRecord;
	}

	@Override
	public int handleOrderwithOrderID(int orderID) {
		// TODO Auto-generated method stub
		int countOfRecord = orderDaoImpl.handleOrderwithOrderID(orderID);
		
		return countOfRecord;
	}

	@Override
	public List<OrdersView> getAllUnReceivedOrders() {
		// TODO Auto-generated method stub
		
		List<OrdersView> allUnReceivedOrders = orderDaoImpl.getAllUnReceivedOrders();
		
		return allUnReceivedOrders;
	}

	@Override
	public int makePendingOrderFalsewithOrderID(int orderID) {
		// TODO Auto-generated method stub
		int countOfRecord = orderDaoImpl.makePendingOrderFalsewithOrderID(orderID);
		return countOfRecord;
	}

	@Override
	public int setTotalamountOfOrderID(int currentOrderID, int totalamount) {
		// TODO Auto-generated method stub
		int countOfRecord = orderDaoImpl.setTotalamountOfOrderID(currentOrderID,totalamount);
		
		return 0;
	}
	
	

}
