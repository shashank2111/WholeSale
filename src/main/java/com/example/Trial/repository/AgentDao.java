package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.Agent;

public interface AgentDao {
	
	public int addAgent(Agent agent);
	public List<Agent> findAllAgentsByAgent(Agent agent);
	public int editAgent(Agent agent);
	public int deleteAgent(Agent agent);
	public List<Agent> getAllAgents();
	
}
