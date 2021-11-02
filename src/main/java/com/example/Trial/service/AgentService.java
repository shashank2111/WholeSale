package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.Agent;

public interface AgentService {
	public int addAgent(Agent agent);
	public List<Agent> findAllAgentsByAgent(Agent agent);
	public int updateAgent(Agent agent);
	public int deleteAgent(Agent agent);
	public List<Agent> getAllAgents();
}
