package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.RequestDetails;

public interface RequestDetailsService {
	
	public int addRequestDetail(RequestDetails rqdetails);
	public List<RequestDetails> findAllDetailswithRequestID(int requestID); 
	public int deleteDetail(RequestDetails rqdetail);
	public int deleteAllDetailswithRequestID(int currentrequestID);
}
