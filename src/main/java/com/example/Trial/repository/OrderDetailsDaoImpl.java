package com.example.Trial.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.OrderDetails;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertOrderDetails(OrderDetails[] ods,int currentOrderID) {
		// TODO Auto-generated method stub
		
		String sql = "Insert into orderdetails (orderID,productID,quantity) Values (?,?,?)";
		
		
		
		jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setInt(1, currentOrderID);
				ps.setInt(2,ods[i].getProductID());
				ps.setInt(3, ods[i].getQuantity());
				
				
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return ods.length;
			}
		});
		
		return 0;
	}

}
