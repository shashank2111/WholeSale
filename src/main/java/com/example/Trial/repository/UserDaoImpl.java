package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.Agent;
import com.example.Trial.model.Customer;
import com.example.Trial.model.Employee;
import com.example.Trial.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
//            user.setId(resultSet.getInt("id"));
//            user.setFirstname(resultSet.getString("firstname"));
//            user.setLastname(resultSet.getString("lastname"));
            user.setEmail(resultSet.getString("email"));
//            user.setPhone(resultSet.getString("phone"));
            user.setRoletitle(resultSet.getString("roletitle"));
//            user.setStatus(resultSet.getString("status"));
            user.setPassword(resultSet.getString("password"));
//            user.setAddress(resultSet.getString("address"));
//            user.setCity(resultSet.getString("city"));
            return user;
        }
    };
	
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		
		String sql = "select * from user where email = ?";
		User user = jdbcTemplate.queryForObject(sql,userRowMapper,email);
		
		return user;
		
//		return null;
	}
	
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user (email,password,roletitle) VALUES (?,?,?)";
		
		String p = "123";
		String res = this.bCryptPasswordEncoder.encode(p);
		System.out.println(res);
		Object[] args = {user.getEmail(),user.getPassword(),user.getRoletitle()};
		jdbcTemplate.update(sql,args);
		
	}

	@Override
	public int saveUserByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "Insert into user(email,password,roletitle) Values(?,?,?)";
		String pass = this.bCryptPasswordEncoder.encode(customer.getPassword());
		Object[] args = {customer.getEmail(),pass,"customer"};
		int result = jdbcTemplate.update(sql,args);
		
		return result;
	}

	@Override
	public int deleteUserByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		String sql = "Delete from user where email = ?";
		Object[] args = {customer.getEmail()};
		int result = jdbcTemplate.update(sql,args);
		return result;
	}

	@Override
	public int saveUserByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String sql = "Insert into user (email,password,roletitle) values (?,?,?)";
		String pass = this.bCryptPasswordEncoder.encode(employee.getPassword());
		Object[] args = {employee.getEmail(),pass,"employee"};
		
		return jdbcTemplate.update(sql,args);
	}

	@Override
	public int deleteUserByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		String sql = "Delete from user where email = ?";
		Object[] args = {employee.getEmail()};
		return jdbcTemplate.update(sql,args);
	}

	@Override
	public int saveUserByAgent(Agent agent) {
		// TODO Auto-generated method stub
		String sql = "Insert into user(email,password,roletitle) values(?,?,?)";
		String pass = this.bCryptPasswordEncoder.encode(agent.getPassword());
		Object[] args = {agent.getEmail(),pass,"agent"};
		return jdbcTemplate.update(sql,args);
		
//		return 0;
	}

	@Override
	public int deleteUserByAgent(Agent agent) {
		// TODO Auto-generated method stub
		String sql = "Delete from user where email = ?";
		Object[] args = {agent.getEmail()};
		System.out.println("agent email = " + agent.getEmail());
		return jdbcTemplate.update(sql, args);
//		return 0;
	}
	
}

