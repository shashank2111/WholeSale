package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.model.RequestDetails;
import com.example.Trial.model.RequestDetailsView;
import com.example.Trial.repository.RequestDetailsDao;

@Service
public class RequestDetailsServiceImpl implements RequestDetailsService {

	@Autowired
	private RequestDetailsDao requestDetailsDaoImpl;
	
	@Override
	public int addRequestDetail(RequestDetails rqdetails) {
		// TODO Auto-generated method stub
		
		int countOfRecord = requestDetailsDaoImpl.addRequestDetail(rqdetails);
		
		return countOfRecord;
	}

	@Override
	public List<RequestDetails> findAllDetailswithRequestID(int requestID) {
		// TODO Auto-generated method stub
		
		List<RequestDetails> rqdetails = requestDetailsDaoImpl.findAllDetailswithRequestID(requestID);
		
		return rqdetails;
	}

	@Override
	public int deleteDetail(RequestDetails rqdetail) {
		// TODO Auto-generated method stub
		int countOfRecord = requestDetailsDaoImpl.deleteRequestDetail(rqdetail);
		return countOfRecord;
	}

	@Override
	public int deleteAllDetailswithRequestID(int currentrequestID) {
		// TODO Auto-generated method stub
		int countOfRecord = requestDetailsDaoImpl.deleteAllDetailswithRequestID(currentrequestID);
		return countOfRecord;
	}

	@Override
	public List<RequestDetailsView> getAllRequestDetailsViewwithRequestID(int requestID) {
		// TODO Auto-generated method stub
		
		List<RequestDetailsView> allRequestDetailsViewwithRequestID = requestDetailsDaoImpl.getAllRequestDetailsViewwithRequestID(requestID);
		
		return allRequestDetailsViewwithRequestID;
	}

}
