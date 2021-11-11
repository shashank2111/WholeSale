package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.RequestDetails;
import com.example.Trial.model.RequestDetailsView;

public interface RequestDetailsService {
	
	public int addRequestDetail(RequestDetails rqdetails);
	public List<RequestDetails> findAllDetailswithRequestID(int requestID); 
	public int deleteDetail(RequestDetails rqdetail);
	public int deleteAllDetailswithRequestID(int currentrequestID);
	public List<RequestDetailsView> getAllRequestDetailsViewwithRequestID(int requestID);
	public int getTotalAmountwithRequestID(int currentrequestID);
}
