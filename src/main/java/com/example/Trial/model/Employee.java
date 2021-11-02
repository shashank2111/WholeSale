package com.example.Trial.model;

public class Employee {
	private int employeeID;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String address;
	private String roletitle;
	private String Doj;
	private String education;
	private String password;
	public Employee() {
		super();
	}
	
	public Employee(String firstname, String lastname, String email, String phone, String address, String roletitle,
			String doj, String education) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.roletitle = roletitle;
		this.Doj = doj;
		this.education = education;
	}

	public String getDoj() {
		return Doj;
	}

	public void setDoj(String doj) {
		Doj = doj;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
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
