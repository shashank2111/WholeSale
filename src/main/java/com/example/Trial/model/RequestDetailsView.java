package com.example.Trial.model;

public class RequestDetailsView {
	private int requestdetailsID;
	private int requestID;
	private int productID;
	private String product;
	private int quantity;
	private int productprice;
	private int totalamount;
	
	public RequestDetailsView() {
		super();
	}

	public RequestDetailsView(int requestdetailsID, int requestID, int productID, String product, int quantity,
			int productprice, int totalamount) {
		super();
		this.requestdetailsID = requestdetailsID;
		this.requestID = requestID;
		this.productID = productID;
		this.product = product;
		this.quantity = quantity;
		this.productprice = productprice;
		this.totalamount = totalamount;
	}

	public int getRequestdetailsID() {
		return requestdetailsID;
	}

	public void setRequestdetailsID(int requestdetailsID) {
		this.requestdetailsID = requestdetailsID;
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

	public int getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}
	
	
	
}
