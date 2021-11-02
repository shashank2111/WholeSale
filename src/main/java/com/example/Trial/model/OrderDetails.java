package com.example.Trial.model;

public class OrderDetails {
	private int orderdetailsID;
	private int orderID;
	private int productID;
	private int quantity;
	private int productprice;
	private int totalprice;
	public OrderDetails() {
		super();
	}
	public OrderDetails(int orderdetailsID, int orderID, int productID, int quantity, int productprice,
			int totalprice) {
		super();
		this.orderdetailsID = orderdetailsID;
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
		this.productprice = productprice;
		this.totalprice = totalprice;
	}
	public int getOrderdetailsID() {
		return orderdetailsID;
	}
	public void setOrderdetailsID(int orderdetailsID) {
		this.orderdetailsID = orderdetailsID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductprice() {
		return productprice;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	
	
	
}
