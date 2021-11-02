package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.model.Customer;
import com.example.Trial.repository.CustomerDao;
import com.example.Trial.repository.UserDao;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDaoImpl;
	@Autowired
	private UserDao userDaoImpl;
	
	@Override
	public int addCustomer(Customer customer) {
		
		int customerID = customerDaoImpl.addCustomer(customer);
		int result = userDaoImpl.saveUserByCustomer(customer);
		return customerID;
	}

	@Override
	public List<Customer> findAllCustomersByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		List<Customer> customers = customerDaoImpl.findAllCustomersByCustomer(customer);
		
		return customers;
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		int countOfrecord = customerDaoImpl.editCustomer(customer);
		return countOfrecord;
	}

	@Override
	public int deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		int countOfRecord = customerDaoImpl.deleteCustomer(customer);
		int result = userDaoImpl.deleteUserByCustomer(customer);
		System.out.println(Integer.toString(countOfRecord) + " records are deleted ");
		return countOfRecord;
	}
}
