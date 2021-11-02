package com.example.Trial.model;

public class Request {
	private int requestID;
	private String requestdate;
	private boolean isinprocess;
	private boolean isfulfilled;
	private int employeeID;
	private int agentID;
	public Request() {
		super();
	}
	public Request(int requestID, String requestdate, boolean isinprocess, boolean isfulfilled, int employeeID,
			int agentID) {
		super();
		this.requestID = requestID;
		this.requestdate = requestdate;
		this.isinprocess = isinprocess;
		this.isfulfilled = isfulfilled;
		this.employeeID = employeeID;
		this.agentID = agentID;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	
	public String getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}
	public boolean isIsinprocess() {
		return isinprocess;
	}
	public void setIsinprocess(boolean isinprocess) {
		this.isinprocess = isinprocess;
	}
	public boolean isIsfulfilled() {
		return isfulfilled;
	}
	public void setIsfulfilled(boolean isfulfilled) {
		this.isfulfilled = isfulfilled;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	
	
}
