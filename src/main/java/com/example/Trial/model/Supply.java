package com.example.Trial.model;

public class Supply {
	private int agentID;
	private int productID;
	public Supply() {
		super();
	}
	public Supply(int agentID, int productID) {
		super();
		this.agentID = agentID;
		this.productID = productID;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	
}
