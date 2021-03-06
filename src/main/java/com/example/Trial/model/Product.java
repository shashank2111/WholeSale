package com.example.Trial.model;

public class Product {
	
	private int productID;
	private String productname;
	private String desc;
	private int costprice;
	private int sellprice;
	private int stock;
	private int alertstock;
	private String brand;
	private int categoryID;
	public Product() {
		super();
	}
	public Product(int productID, String productname, String desc, int costprice, int sellprice, int stock,
			int alertstock, String brand, int categoryID) {
		super();
		this.productID = productID;
		this.productname = productname;
		this.desc = desc;
		this.costprice = costprice;
		this.sellprice = sellprice;
		this.stock = stock;
		this.alertstock = alertstock;
		this.brand = brand;
		this.categoryID = categoryID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getSellprice() {
		return sellprice;
	}
	public void setSellprice(int sellprice) {
		this.sellprice = sellprice;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getCostprice() {
		return costprice;
	}

	public void setCostprice(int costprice) {
		this.costprice = costprice;
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getAlertstock() {
		return alertstock;
	}
	public void setAlertstock(int alertstock) {
		this.alertstock = alertstock;
	}

}
