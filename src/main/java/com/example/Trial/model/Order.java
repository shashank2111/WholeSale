package com.example.Trial.model;

public class Order {
	private int orderID;
	private String orderdate;
	private boolean orderIsPending;
	private int totalamount;
	private int customerID;
	private int employeeID;
	private boolean iscancelled;
	private boolean ishandled;
	public Order() {
		super();
	}
	
	
	public Order(String orderdate, boolean orderIsPending, int totalamount, int customerID, int employeeID,
			boolean iscancelled, boolean ishandled) {
		super();
		this.orderdate = orderdate;
		this.orderIsPending = orderIsPending;
		this.totalamount = totalamount;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.iscancelled = iscancelled;
		this.ishandled = ishandled;
	}


	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	
	public boolean isOrderIsPending() {
		return orderIsPending;
	}


	public void setOrderIsPending(boolean orderIsPending) {
		this.orderIsPending = orderIsPending;
	}


	public int getTotalamount() {
		return totalamount;
	}


	public boolean isIscancelled() {
		return iscancelled;
	}

	public void setIscancelled(boolean iscancelled) {
		this.iscancelled = iscancelled;
	}

	public void setTotalamount(int totalamount) {
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


	public boolean isIshandled() {
		return ishandled;
	}


	public void setIshandled(boolean ishandled) {
		this.ishandled = ishandled;
	}
	
	
}
