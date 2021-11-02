package com.example.Trial.model;

public class User {
	private String email;
	private String password;
	private String roletitle;
	public User() {
		super();
	}
	public User(String email, String password, String roletitle) {
		super();
		this.email = email;
		this.password = password;
		this.roletitle = roletitle;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoletitle() {
		return roletitle;
	}
	public void setRoletitle(String roletitle) {
		this.roletitle = roletitle;
	}
	
	
	
}
