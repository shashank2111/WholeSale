package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.model.Request;
import com.example.Trial.model.RequestDetailsView;
import com.example.Trial.repository.RequestDao;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired 
    private RequestDao requestDaoImpl;
	
	@Override
	public int createNewRequest(int agentID) {
		// TODO Auto-generated method stub
		
//		String sql = "select * from request where requestID = (select MAX(requestID) from request)";
//		
//		Request rq = 
		int currentRequestID = requestDaoImpl.getCurrentRequestID(agentID);
		
		return currentRequestID;
	}

	@Override
	public int deleteRequest(int currentrequestID) {
		// TODO Auto-generated method stub
		int countOfRecord = requestDaoImpl.deleteRequest(currentrequestID);
		return 0;
	}

	@Override
	public List<Request> getPendingRequeststome(int agentID) {
		// TODO Auto-generated method stub
		List<Request> PendingRequeststome = requestDaoImpl.getPendingRequeststome(agentID);
		
		
		return PendingRequeststome;
	}

	@Override
	public List<Request> getunFulfilledRequestsbyme(int agentID) {
		// TODO Auto-generated method stub
		
		List<Request> FulfilledRequestbyme = requestDaoImpl.getunFulfilledRequestsbyme(agentID);
		
		return FulfilledRequestbyme;
	}

	@Override
	public int makeisinprocesstrue(int requestID) {
		// TODO Auto-generated method stub
		int countOfRecord = requestDaoImpl.makeisinprocesstrue(requestID);
		return countOfRecord;
	}

	@Override
	public List<Request> getAllRequestsisinprocessfalse() {
		// TODO Auto-generated method stub
		
		List<Request> requestsnotshipped = requestDaoImpl.getAllRequestsisinprocessfalse();
		
		return requestsnotshipped;
	}

	@Override
	public List<Request> getAllRequestsisinprocesstrueisfulfilledfalse() {
		// TODO Auto-generated method stub
		List<Request> requestsshippednotreceived = requestDaoImpl.getAllRequestsisinprocesstrueisfulfilledfalse();
		
		return requestsshippednotreceived;
	}

	@Override
	public int receiveImportsetisfulfilledtrue(int requestID) {
		// TODO Auto-generated method stub
		
		int countOfRecord = requestDaoImpl.receiveImportsetisfulfilledtrue(requestID);
		
		return countOfRecord;
	}

	@Override
	public List<Request> getAllMyExports(int agentID) {
		// TODO Auto-generated method stub
		
		List<Request> allMyExports = requestDaoImpl.getAllMyExports(agentID);
		
		return allMyExports;
	}

	@Override
	public List<Request> getAllImports() {
		// TODO Auto-generated method stub
		
		List<Request> allImports = requestDaoImpl.getAllImports();
		return allImports;
	}

	@Override
	public int getAgentUsingRequestId(int currentRequestID) {
		// TODO Auto-generated method stub
		
		int agentId = requestDaoImpl.getAgentUsingRequestId(currentRequestID);
		
		return agentId;
	}

	@Override
	public int setRequestTotalamount(int currentrequestID, int totalamount) {
		// TODO Auto-generated method stub
		
		int countOfRecord = requestDaoImpl.setRequestTotalamount(currentrequestID,totalamount);
		
		return 0;
	}

	

	
}
