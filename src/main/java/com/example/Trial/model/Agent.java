package com.example.Trial.model;

public class Agent {
	private int agentID;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String address;
	private String roletitle;
	private String password;
	public Agent() {
		super();
	}
	public Agent(int agentID, String firstname, String lastname, String email, String phone, String address,
			String roletitle, String password) {
		super();
		this.agentID = agentID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.roletitle = roletitle;
		this.password = password;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRoletitle() {
		return roletitle;
	}
	public void setRoletitle(String roletitle) {
		this.roletitle = roletitle;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
