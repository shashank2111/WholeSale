package com.example.Trial.model;

import java.util.List;

public class OrderDetailsWrapper {
	
	public List<OrderDetails> orders;

	public OrderDetailsWrapper(List<OrderDetails> orders) {
		super();
		this.orders = orders;
	}

	public OrderDetailsWrapper() {
		super();
	}

	public List<OrderDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}
	
	
	
}
