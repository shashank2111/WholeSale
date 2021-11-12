package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.Agent;
import com.example.Trial.model.SupplyView;

public interface SupplyService {
	public List<SupplyView> getAllProductssuppliedByAgentID(int agentID);
	public int addAgentsforProduct(int productID,Agent[] agents);
	
}
