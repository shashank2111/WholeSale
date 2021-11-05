package com.example.Trial.repository;

public interface RequestDao {
	public int getCurrentRequestID(int agentID);
	public int deleteRequest(int currentrequestID);
}
