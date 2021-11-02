package com.example.Trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.repository.RequestDao;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired 
    private RequestDao requestDaoImpl;
	
	@Override
	public int createNewRequest() {
		// TODO Auto-generated method stub
		
//		String sql = "select * from request where requestID = (select MAX(requestID) from request)";
//		
//		Request rq = 
		int currentRequestID = requestDaoImpl.getCurrentRequestID();
		
		return currentRequestID;
	}

	@Override
	public int deleteRequest(int currentrequestID) {
		// TODO Auto-generated method stub
		int countOfRecord = requestDaoImpl.deleteRequest(currentrequestID);
		return 0;
	}

}
