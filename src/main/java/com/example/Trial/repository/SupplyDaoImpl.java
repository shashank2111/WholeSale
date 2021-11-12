package com.example.Trial.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.Agent;
import com.example.Trial.model.RequestDetailsView;
import com.example.Trial.model.SupplyView;


@Repository
public class SupplyDaoImpl implements SupplyDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<SupplyView> supplyviewRowMapper = new RowMapper<SupplyView>() {
		@Override
		public SupplyView mapRow(ResultSet resultSet, int i) throws SQLException{
			SupplyView supplyview = new SupplyView();
			supplyview.setAgentID(resultSet.getInt("agentID"));
			supplyview.setEmail(resultSet.getString("email"));
			supplyview.setProductID(resultSet.getInt("productID"));
			supplyview.setProduct(resultSet.getString("product"));
			supplyview.setSellprice(resultSet.getInt("sellprice"));
			return supplyview;
		}
	};
	
	@Override
	public List<SupplyView> getAllProductssuppliedByAgentID(int agentID) {
		// TODO Auto-generated method stub
		String sql ="select * from supplyview where agentID = ?";
		Object[] args = {agentID};
		
		List<SupplyView> allProductsByAgentID =  jdbcTemplate.query(sql,supplyviewRowMapper,args);
		
		return allProductsByAgentID;
	}

	@Override
	public int addAgentsforProduct(int productID, Agent[] agents) {
		// TODO Auto-generated method stub
		String sql ="Insert into supply(agentID,productID) values(?,?)";
		
		int[] cR = jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, agents[i].getAgentID());
				ps.setInt(2, productID);
				
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return agents.length;
			}
		});
		
		return 0;
	}

}
