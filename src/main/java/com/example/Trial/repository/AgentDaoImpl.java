package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Trial.model.Agent;

@Repository
public class AgentDaoImpl implements AgentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Agent> agentRowMapper = new RowMapper<Agent>() {
        @Override
        public Agent mapRow(ResultSet resultSet, int i) throws SQLException {
            Agent agent = new Agent();
            agent.setAgentID(resultSet.getInt("agentID"));
            agent.setFirstname(resultSet.getString("firstname"));
            agent.setLastname(resultSet.getString("lastname"));
            agent.setEmail(resultSet.getString("email"));
            agent.setPhone(resultSet.getString("phone"));
            agent.setAddress(resultSet.getString("address"));
            return agent;
        }
    };
	
	@Override
	@Transactional
	public int addAgent(Agent agent) {
		// TODO Auto-generated method stub
		
		String sql = "Insert into agent(firstname,lastname,email,phone,address,roletitle) values(?,?,?,?,?,?)";
		Object[] args = {agent.getFirstname(),agent.getLastname(),agent.getEmail(),agent.getPhone(),agent.getAddress(),"agent"};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		sql = "select * from agent where email = ?";
		Object[] args1 = {agent.getEmail()};
		
		Agent agent1 = jdbcTemplate.queryForObject(sql, agentRowMapper,args1);
		System.out.println("AgentId = " + Integer.toString(agent1.getAgentID()) + "email = " + agent1.getEmail());
		
		return agent1.getAgentID();
		
	}

	@Override
	public List<Agent> findAllAgentsByAgent(Agent agent) {
		// TODO Auto-generated method stub
		String sql = "Select * from agent where email = ?";
		Object[] args = {agent.getEmail()};
		List<Agent> agents = jdbcTemplate.query(sql, agentRowMapper,args);
		return agents;
	}

	@Override
	public int editAgent(Agent agent) {
		// TODO Auto-generated method stub
		String sql = "Update agent set firstname = ?,lastname = ?,phone = ?,address = ? where email = ?";
		Object[] args = {agent.getFirstname(),agent.getLastname(),agent.getPhone(),agent.getAddress(),agent.getEmail()};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}

	@Override
	public int deleteAgent(Agent agent) {
		// TODO Auto-generated method stub
		String sql = "Delete from agent where email = ?";
		Object[] args = {agent.getEmail()};
		
		return jdbcTemplate.update(sql,args);
	}

	@Override
	public List<Agent> getAllAgents() {
		// TODO Auto-generated method stub
		String sql = "select * from agent";
		
		
		List<Agent> allAgents = jdbcTemplate.query(sql, agentRowMapper);
		return allAgents;
	}

	@Override
	public Agent getAgentByEmail(String email) {
		// TODO Auto-generated method stub
		
		String sql = "Select * from agent where email = ?";
		Object[] args = {email};
		
		Agent agent = jdbcTemplate.queryForObject(sql, agentRowMapper,args);
		
		return agent;
	}

}
