package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Trial.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerID(resultSet.getInt("customerID"));
            customer.setFirstname(resultSet.getString("firstname"));
            customer.setLastname(resultSet.getString("lastname"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPhone(resultSet.getString("phone"));
            customer.setAddress(resultSet.getString("address"));
            return customer;
        }
    };
	
	@Override
	@Transactional
	public int addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "Insert into customer (firstname,lastname,address,phone,email) Values (?,?,?,?,?)";
		Object[] args = {customer.getFirstname(),customer.getLastname(),customer.getAddress(),customer.getPhone(),customer.getEmail()};

		//		userDaoImpl.saveUser(Customer customer);
		
//		sql = "Insert into user(email,password,roletitle) values (?,?,?) where email = ?";
//		Object[] args2 = {customer.getEmail(),customer.getPassword(),customer.getRoletitle()};
//		
//		jdbcTemplate.update(sql,args2);
		
		int count  = jdbcTemplate.update(sql,args);
		System.out.println(customer.getEmail());
		sql = "select * from customer where email = ?  " ;
		Object[] args1 = {customer.getEmail()};
		Customer customer1 = jdbcTemplate.queryForObject(sql,customerRowMapper,args1);
		System.out.println(customer1.toString());
		System.out.println(customer1.getCustomerID());
		return customer1.getCustomerID();
	}

	@Override
	@Transactional
	public List<Customer> findAllCustomersByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "select * from customer where email LIKE ?";
		Object[] args = {customer.getEmail()};
		
		List<Customer> customers = jdbcTemplate.query(sql, customerRowMapper,args);
		return customers;
//		return null;
	}

	@Override
	public int editCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		String sql1 = "Update customer set firstname = ? , lastname = ? , address = ? ,phone = ? where email = ?";
		Object[]  args1 = {customer.getFirstname(),customer.getLastname(),customer.getAddress(),customer.getPhone(),customer.getEmail()}; 
		
		int countOfRecord = jdbcTemplate.update(sql1,args1);
		return countOfRecord;
	}

	@Override
	public int deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "Delete from customer where email = ?";
		Object[] args = {customer.getEmail()};
		int countOfRecord = jdbcTemplate.update(sql,args);
		
//		userImpl.deleteUser(Customer customer);
		
		return countOfRecord;

	}

	@Override
	public Customer getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		
		String sql = "select * from customer where email = ?";
		Object[] args = {email};
		
		Customer customer = jdbcTemplate.queryForObject(sql, customerRowMapper,args);
		
		return customer;
	}

}
