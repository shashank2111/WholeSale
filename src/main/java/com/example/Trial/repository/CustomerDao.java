package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.Customer;

public interface CustomerDao {
	
	public int addCustomer(Customer customer);
	public List<Customer> findAllCustomersByCustomer(Customer customer);
	
	public int editCustomer(Customer customer);
	public int deleteCustomer(Customer customer);
	public Customer getCustomerByEmail(String email);
	public List<Customer> getAllCustomers();
}
