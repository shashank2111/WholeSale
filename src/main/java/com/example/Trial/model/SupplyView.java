package com.example.Trial.model;

public class SupplyView {
	private int agentID;
	private String email;
	private int productID;
	private String product;
	private int sellprice;
	public SupplyView() {
		super();
	}
	public SupplyView(int agentID, String email, int productID, String product, int sellprice) {
		super();
		this.agentID = agentID;
		this.email = email;
		this.productID = productID;
		this.product = product;
		this.sellprice = sellprice;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getSellprice() {
		return sellprice;
	}
	public void setSellprice(int sellprice) {
		this.sellprice = sellprice;
	}
	
	 
}
