package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.SupplyView;

public interface SupplyDao {
	List<SupplyView> getAllProductssuppliedByAgentID(int agentID);
}
