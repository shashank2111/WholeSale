package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.Order;
import com.example.Trial.model.OrdersView;

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
            order.setIscancelled(resultSet.getBoolean("iscancelled"));
            order.setIshandled(resultSet.getBoolean("ishandled"));
            return order;
        }
    };
    
    private RowMapper<OrdersView> ordersViewRowMapper = new RowMapper<OrdersView>() {
        @Override
        public OrdersView mapRow(ResultSet resultSet, int i) throws SQLException {
            OrdersView ordersView = new OrdersView();
            ordersView.setOrderID(resultSet.getInt("orderID"));
            ordersView.setOrderdate(resultSet.getString("orderdate"));
            ordersView.setOrderIsPending(resultSet.getBoolean("orderispending"));
            ordersView.setTotalamount(resultSet.getInt("totalamount"));
            ordersView.setCustomerID(resultSet.getInt("customerID"));
            ordersView.setCustomeremail(resultSet.getString("customeremail"));
            ordersView.setIshandled(resultSet.getBoolean("ishandled"));
            ordersView.setIscancelled(resultSet.getBoolean("iscancelled"));
            return ordersView;
        }
    };
    
	
	
	@Override
	public int createNewOrder(int customerID) {
		// TODO Auto-generated method stub
		
//		"The data base orders will/should have one row with values (0,"0000-00-00",true,0,0,0)";
		String sql = "select * from orders where orderID = (select MAX(orderID) from orders)";
		Order order = jdbcTemplate.queryForObject(sql, orderRowMapper);
		int maxID = order.getOrderID();
		String sql1 = "Insert into orders(orderID,orderdate,orderispending,totalamount,customerID,iscancelled,ishandled) values(?,?,?,?,?,?,?)";
//																								iscancelled => false	
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = dateFormat.format(date);
		Object[] args = {maxID+1,strDate,true,0,customerID,false,false};
		jdbcTemplate.update(sql1,args);
		return maxID+1;

	}


	@Override
	public List<Order> getMyPendingOrders(int customerID) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where customerID = ? and customerId != 0 and orderispending = ? and iscancelled = ?";
//																										and iscancelled => false
		Object[] args = {customerID,true,false};
		
		List<Order> myOrders = jdbcTemplate.query(sql, orderRowMapper, args);
		
		return myOrders;
	}


	@Override
	public List<Order> getMyFulfilledOrders(int customerID) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where customerID = ? and (orderispending = ? or iscancelled = ? ) and customerID != 0";
//																				 or iscancelled => true
				
		Object[] args = {customerID,false,true};
						
		List<Order> myOrders = jdbcTemplate.query(sql, orderRowMapper, args);
						
		return myOrders;
	}


	@Override
	public List<OrdersView> getAllUnAttendedOrders() {
		// TODO Auto-generated method stub
		String sql = "select * from ordersview where ishandled = ? and customerID != 0";
		Object[] args = {false};
		
		List<OrdersView> AllUnAttendedOrders = jdbcTemplate.query(sql,ordersViewRowMapper,args);
		
		return AllUnAttendedOrders;
	}


	@Override
	public int cancelOrderwithOrderID(int orderID) {
		// TODO Auto-generated method stub
		
		String sql = "Update orders set iscancelled  = ? where orderID = ?";
		Object[] args = {true,orderID};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}


	@Override
	public int handleOrderwithOrderID(int orderID) {
		// TODO Auto-generated method stub
		String sql = "Update orders set ishandled = ? where orderID = ?";
		Object[] args= {true,orderID};
		int countOfRecord = jdbcTemplate.update(sql,args);
		return countOfRecord;
	}


	@Override
	public List<OrdersView> getAllUnReceivedOrders() {
		// TODO Auto-generated method stub		
		String sql = "select * from ordersview where ishandled = ? and orderispending = ? and customerID != 0";
		Object[] args = {true,true};
		
		List<OrdersView> AllUnReceivedOrders = jdbcTemplate.query(sql,ordersViewRowMapper,args);
		for(OrdersView ov:AllUnReceivedOrders) {
			System.out.println(ov.isOrderIsPending());
			System.out.println(ov.isIshandled());
		}
		return AllUnReceivedOrders;
	}


	@Override
	public int makePendingOrderFalsewithOrderID(int orderID) {
		// TODO Auto-generated method stub
		String sql = "update orders set orderispending = ? where orderID = ?";
		Object[] args = {false,orderID};
		int countOfRecord = jdbcTemplate.update(sql,args);
		return countOfRecord;
	}


	@Override
	public int setTotalamountOfOrderID(int currentOrderID, int totalamount) {
		// TODO Auto-generated method stub
		
		String sql = "Update orders set totalamount = ? where orderID = ?";
		Object[] args = {totalamount , currentOrderID};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		return countOfRecord;
	}

}
