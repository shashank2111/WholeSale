package com.example.Trial.model;

import java.util.Arrays;
import java.util.List;

public class RequestDetailsWrapper {
	
	private RequestDetails rqd;
	
	private List<RequestDetails> details;

	public RequestDetailsWrapper(List<RequestDetails> details) {
		super();
		this.details = details;
	}

	public RequestDetailsWrapper() {
		super();
		this.rqd = new RequestDetails(); 
		this.rqd.setProductID(-1);
		this.rqd.setQuantity(-1);
		this.rqd.setProductprice(-1);
		this.details = Arrays.asList(rqd); 
	}
	
	

	public List<RequestDetails> getDetails() {
		return details;
	}

	public void setDetails(List<RequestDetails> details) {
		this.details = details;
	}
	 
	public void addDetail(RequestDetails detail) {
		this.details.add(detail);
	}
	
	@Override
	public String toString() {
		return "RequestDetailsWrapper [details=" + details + "]";
	}
	
	
	
}
