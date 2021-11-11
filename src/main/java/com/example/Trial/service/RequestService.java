package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.Request;

public interface RequestService {
	public int createNewRequest(int agentId);
	public int deleteRequest(int currentrequestID);
	public List<Request> getPendingRequeststome(int agentID);
	public List<Request> getunFulfilledRequestsbyme(int agentID);
	public int makeisinprocesstrue(int requestID);
	public List<Request> getAllRequestsisinprocessfalse();
	public List<Request> getAllRequestsisinprocesstrueisfulfilledfalse();
	public int receiveImportsetisfulfilledtrue(int requestID);
	public List<Request> getAllMyExports(int agentID);
	public List<Request> getAllImports();
	public int getAgentUsingRequestId(int currentRequestID);
	public int setRequestTotalamount(int currentrequestID,int totalamount);
}
