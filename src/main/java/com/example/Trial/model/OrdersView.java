package com.example.Trial.model;

public class OrdersView {
	private int orderID;
	private String orderdate;
	private boolean orderIsPending;
	private int totalamount;
	private int customerID;
	private int employeeID;
	private boolean iscancelled;
	private boolean ishandled;
	private String customeremail;
	public OrdersView() {
		super();
	}
	
	public OrdersView(int orderID, String orderdate, boolean orderIsPending, int totalamount, int customerID,
			int employeeID, boolean iscancelled, boolean ishandled, String customeremail) {
		super();
		this.orderID = orderID;
		this.orderdate = orderdate;
		this.orderIsPending = orderIsPending;
		this.totalamount = totalamount;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.iscancelled = iscancelled;
		this.ishandled = ishandled;
		this.customeremail = customeremail;
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
	public String getCustomeremail() {
		return customeremail;
	}
	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}

	public boolean isIscancelled() {
		return iscancelled;
	}

	public void setIscancelled(boolean iscancelled) {
		this.iscancelled = iscancelled;
	}

	public boolean isIshandled() {
		return ishandled;
	}

	public void setIshandled(boolean ishandled) {
		this.ishandled = ishandled;
	}
	
	
	
	
}
