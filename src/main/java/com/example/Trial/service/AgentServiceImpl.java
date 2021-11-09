package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Trial.model.Agent;
import com.example.Trial.repository.AgentDao;
import com.example.Trial.repository.UserDao;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {
	
	@Autowired
	private AgentDao agentDaoImpl;
	
	@Autowired
	private UserDao userDaoImpl;
	
	@Autowired
	private EmailSenderService service;
		
	@Override
	@Transactional
	public int addAgent(Agent agent) {
		// TODO Auto-generated method stub
		
		int agentId = agentDaoImpl.addAgent(agent);
		int result = userDaoImpl.saveUserByAgent(agent);
		
		System.out.println("Here the agent ID is "+ Integer.toString(agentId));
		
		service.sendEmail(agent);
		
		return agentId;
		
	}

	@Override
	public List<Agent> findAllAgentsByAgent(Agent agent) {
		// TODO Auto-generated method stub
		List<Agent> agents = agentDaoImpl.findAllAgentsByAgent(agent);
		
		
		return agents;
	}

	@Override
	public int updateAgent(Agent agent) {
		// TODO Auto-generated method stub
		int countOfRecord = agentDaoImpl.editAgent(agent);
		return countOfRecord;
	}

	@Override
	@Transactional
	public int deleteAgent(Agent agent) {
		// TODO Auto-generated method stub
		int countOfRecord = agentDaoImpl.deleteAgent(agent);
		int result = userDaoImpl.deleteUserByAgent(agent);
		
		return countOfRecord;
	}

	@Override
	public List<Agent> getAllAgents() {
		// TODO Auto-generated method stub
		List<Agent> allAgents = agentDaoImpl.getAllAgents();
		return allAgents;
	}

	@Override
	public Agent getAgentByEmail(String email) {
		// TODO Auto-generated method stub
		
		Agent agent = agentDaoImpl.getAgentByEmail(email);
		
		return agent;
	}

}
