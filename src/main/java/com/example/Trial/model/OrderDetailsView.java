package com.example.Trial.model;

public class OrderDetailsView {
	private int orderID;
	private int orderdetailsID;
	private int productID;
	private String product;
	private int quantity;
	private int productprice;
	private int amount;
	public OrderDetailsView() {
		super();
	}
	public OrderDetailsView(int orderID, int orderdetailsID, int productID, String product, int quantity,
			int productprice, int amount) {
		super();
		this.orderID = orderID;
		this.orderdetailsID = orderdetailsID;
		this.productID = productID;
		this.product = product;
		this.quantity = quantity;
		this.productprice = productprice;
		this.amount = amount;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getOrderdetailsID() {
		return orderdetailsID;
	}
	public void setOrderdetailsID(int orderdetailsID) {
		this.orderdetailsID = orderdetailsID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
