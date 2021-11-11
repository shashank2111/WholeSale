package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

}
