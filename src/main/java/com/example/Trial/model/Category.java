package com.example.Trial.model;

public class Category {
	private int categoryID;
	private String categoryname;
	private int roomno;
	public Category() {
		super();
	}
	public Category(int categoryID, String categoryname, int roomno) {
		super();
		this.categoryID = categoryID;
		this.categoryname = categoryname;
		this.roomno = roomno;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}
	
}	
