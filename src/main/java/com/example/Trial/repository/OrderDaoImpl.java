package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Order> orderRowMapper = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order = new Order();
            order.setOrderID(resultSet.getInt("orderID"));
            order.setOrderdate(resultSet.getString("orderdate"));
            order.setOrderIsPending(resultSet.getBoolean("orderispending"));
            order.setTotalamount(resultSet.getInt("totalamount"));
            order.setCustomerID(resultSet.getInt("customerID"));
            order.setEmployeeID(resultSet.getInt("employeeID"));
            return order;
        }
    };
	
	
	@Override
	public int createNewOrder(int customerID) {
		// TODO Auto-generated method stub
		
//		"The data base orders will/should have one row with values (0,"0000-00-00",true,0,0,0)";
		String sql = "select * from orders where orderID = (select MAX(orderID) from orders)";
		Order order = jdbcTemplate.queryForObject(sql, orderRowMapper);
		int maxID = order.getOrderID();
		String sql1 = "Insert into orders(orderID,orderdate,orderispending,totalamount,customerID) values(?,?,?,?,?)";
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		String strDate = dateFormat.format(date);
		Object[] args = {maxID+1,strDate,true,0,customerID};
		jdbcTemplate.update(sql1,args);
		return maxID+1;
//		System.out.println("here");
//		String sql = "select count(*) from orders";	
//    	int count = jdbcTemplate.queryForObject(sql,Integer.class);
//    	System.out.println("count = " + Integer.toString(count));
//    	if(count == 0) {
//    		
//    		String sql1 = "Insert into request(requestId,requestdate,isinprocess,isfulfilled,agentID) values (?,?,?,?,?)";
//    		Date date = Calendar.getInstance().getTime();  
//    		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
//    		String strDate = dateFormat.format(date);
//    		Object[] args = {1,strDate,false,false,agentID};
//    		jdbcTemplate.update(sql1,args);
//    		return 1;
//    	}
//		sql = "select * from request where requestID = (select MAX(requestID) from request)";
//		
//		Request rq = jdbcTemplate.queryForObject(sql, requestRowMapper);
//		int maxId = rq.getRequestID();
//		
//		
//		String sql1 = "Insert into request(requestId,requestdate,isinprocess,isfulfilled,agentID) values(?,?,?,?,?)";
//		Date date = Calendar.getInstance().getTime();  
//		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
//		String strDate = dateFormat.format(date);
//		Object[] args = {maxId+1,strDate,false,false,agentID};
//		jdbcTemplate.update(sql1,args);
		
//		return maxId + 1;
				
//		return 0;
	}

}
