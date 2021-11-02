package com.example.Trial.model;

import java.util.Date;

public class Order {
	private int orderID;
	private Date orderdate;
	private String orderIsPending;
	private String totalamount;
	private int customerID;
	private int employeeID;
	public Order() {
		super();
	}
	
	public Order(Date orderdate, String orderIsPending, String totalamount, int customerID, int employeeID) {
		super();
		this.orderdate = orderdate;
		this.orderIsPending = orderIsPending;
		this.totalamount = totalamount;
		this.customerID = customerID;
		this.employeeID = employeeID;
	}

	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getOrderIsPending() {
		return orderIsPending;
	}
	public void setOrderIsPending(String orderIsPending) {
		this.orderIsPending = orderIsPending;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	
}
