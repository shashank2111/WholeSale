package com.example.Trial.model;

public class RequestDetails {
	private int requestDetailsID;
	private int requestID;
	private int productID;
	private int quantity;
	private int productprice;
	private int totalamount;
	public RequestDetails() {
		super();
	}
	
	
	public RequestDetails(int productID, int quantity, int productprice) {
		super();
		this.productID = productID;
		this.quantity = quantity;
		this.productprice = productprice;
		this.requestDetailsID = 0;
		this.requestID = 0;
		this.totalamount = 0;	
		
	}

	public RequestDetails(int requestID) {
		super();
		this.requestID = requestID;
	}

	public RequestDetails(int requestID, int productID, int quantity, int productprice, int totalamount) {
		super();
		this.requestID = requestID;
		this.productID = productID;
		this.quantity = quantity;
		this.productprice = productprice;
		this.totalamount = totalamount;
	}
	public int getRequestDetailsID() {
		return requestDetailsID;
	}
	public void setRequestDetailsID(int requestDetailsID) {
		this.requestDetailsID = requestDetailsID;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
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
	public int getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}
	
	
	
}
