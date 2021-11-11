package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.SupplyView;

public interface SupplyService {
	List<SupplyView> getAllProductssuppliedByAgentID(int agentID);
	
}
