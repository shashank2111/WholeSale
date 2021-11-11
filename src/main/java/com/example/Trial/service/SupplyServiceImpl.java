package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.model.SupplyView;
import com.example.Trial.repository.SupplyDao;

@Service
public class SupplyServiceImpl implements SupplyService {

	@Autowired
	private SupplyDao supplyDaoImpl;
	
	@Override
	public List<SupplyView> getAllProductssuppliedByAgentID(int agentID) {
		// TODO Auto-generated method stub
		
		List<SupplyView> allProductsByAgentID =  supplyDaoImpl.getAllProductssuppliedByAgentID(agentID);
		
		return allProductsByAgentID;
	}

}
