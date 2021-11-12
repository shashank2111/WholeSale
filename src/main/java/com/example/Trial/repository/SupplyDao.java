package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.Agent;
import com.example.Trial.model.SupplyView;

public interface SupplyDao {
	List<SupplyView> getAllProductssuppliedByAgentID(int agentID);
	public int addAgentsforProduct(int productID, Agent[] agents);
}
