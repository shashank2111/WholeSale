package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.Customer;

public interface CustomerService {
	public int addCustomer(Customer customer);
	public List<Customer> findAllCustomersByCustomer(Customer customer);
	public int updateCustomer(Customer customer);
	public int deleteCustomer(Customer customer);
	
}
