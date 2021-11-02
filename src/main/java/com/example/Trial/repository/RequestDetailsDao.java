package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.RequestDetails;

public interface RequestDetailsDao {
	
	public int addRequestDetail(RequestDetails rqdetail);
	public List<RequestDetails> findAllDetailswithRequestID(int requestID);
	public int deleteRequestDetail(RequestDetails rqdetail);
	public int deleteAllDetailswithRequestID(int currentrequestID);
}
