package com.example.Trial.model;

import java.util.List;

public class OrderDetailsWrapper {
	
	List<OrderDetails> orders;

	public OrderDetailsWrapper() {
		super();
	}

	public List<OrderDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}

	public OrderDetailsWrapper(List<OrderDetails> orders) {
		super();
		this.orders = orders;
	}

	
	public void tostring() {
		for(OrderDetails od : this.orders) {
			System.out.println(od.toString());
		}
	}
	
	
}
