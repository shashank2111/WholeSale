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

import com.example.Trial.model.OrderDetails;
import com.example.Trial.model.OrderDetailsView;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<OrderDetailsView> ordersDetailsViewRowMapper = new RowMapper<OrderDetailsView>() {
        @Override
        public OrderDetailsView mapRow(ResultSet resultSet, int i) throws SQLException {
        	OrderDetailsView orderDetailsView = new OrderDetailsView();
        	orderDetailsView.setOrderID(resultSet.getInt("orderID"));
        	orderDetailsView.setOrderdetailsID(resultSet.getInt("orderdetailsID"));
        	orderDetailsView.setProductID(resultSet.getInt("productID"));
        	orderDetailsView.setProduct(resultSet.getString("product"));
        	orderDetailsView.setQuantity(resultSet.getInt("quantity"));
        	orderDetailsView.setProductprice(resultSet.getInt("productprice"));
        	orderDetailsView.setAmount(resultSet.getInt("amount"));
            return orderDetailsView;
        }
    };
	
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

	@Override
	public List<OrderDetailsView> getOrderDetailsViewbyOrderID(int orderID) {
		// TODO Auto-generated method stub
		
		String sql = "Select * from orderdetailsView where orderID = ?";
		Object[] args = {orderID};
		
		List<OrderDetailsView> allOrderswithID = jdbcTemplate.query(sql,ordersDetailsViewRowMapper,args);
		
		return allOrderswithID;
	}

	@Override
	public int getTotalamountOfOrderID(int currentOrderID) {
		// TODO Auto-generated method stub
		String sql = "select sum(amount) as totalamount from orderdetailsview where orderID = ? group by orderID";
		Object[] args = {currentOrderID};
		
		int totalamount = jdbcTemplate.queryForObject(sql,Integer.class , args);
		return totalamount;
	}

}
