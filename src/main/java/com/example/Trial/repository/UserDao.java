package com.example.Trial.repository;

import com.example.Trial.model.Agent;
import com.example.Trial.model.Customer;
import com.example.Trial.model.Employee;
import com.example.Trial.model.User;

public interface UserDao {
	public User findByEmail(String email);
	public void save(User user);
	public int saveUserByCustomer(Customer customer);
	public int deleteUserByCustomer(Customer customer);
	public int saveUserByEmployee(Employee employee);
	public int deleteUserByEmployee(Employee employee);
	public int saveUserByAgent(Agent agent);
	public int deleteUserByAgent(Agent agent);
}
