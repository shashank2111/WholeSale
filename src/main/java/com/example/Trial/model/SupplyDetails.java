package com.example.Trial.model;

public class SupplyDetails {
	private int supplydetailsID;
	private int productprice;
	private int productID;
	private int quantity;
	private int totalamount;
	private int agentID;
	public SupplyDetails() {
		super();
	}
	public SupplyDetails(int productprice, int productID, int quantity, int totalamount, int agentID) {
		super();
		this.productprice = productprice;
		this.productID = productID;
		this.quantity = quantity;
		this.totalamount = totalamount;
		this.agentID = agentID;
	}
	public int getSupplydetailsID() {
		return supplydetailsID;
	}
	public void setSupplydetailsID(int supplydetailsID) {
		this.supplydetailsID = supplydetailsID;
	}
	public int getProductprice() {
		return productprice;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
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
	public int getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	
	
}
