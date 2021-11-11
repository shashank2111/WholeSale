package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.RequestDetails;
import com.example.Trial.model.RequestDetailsView;

public interface RequestDetailsDao {
	
	public int addRequestDetail(RequestDetails rqdetail);
	public List<RequestDetails> findAllDetailswithRequestID(int requestID);
	public int deleteRequestDetail(RequestDetails rqdetail);
	public int deleteAllDetailswithRequestID(int currentrequestID);
	public List<RequestDetailsView> getAllRequestDetailsViewwithRequestID(int requestID);
	public int getTotalAmountwithRequestID(int currentrequestID);
}
