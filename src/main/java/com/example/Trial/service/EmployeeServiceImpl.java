package com.example.Trial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Trial.model.Employee;
import com.example.Trial.repository.EmployeeDao;
import com.example.Trial.repository.EmployeeDaoImpl;
import com.example.Trial.repository.UserDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDaoImpl;
	
	@Autowired
	private UserDao userDaoImpl;
	
	@Override
	@Transactional
	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		int employeeId = employeeDaoImpl.addEmployee(employee);
		int result = userDaoImpl.saveUserByEmployee(employee);
		
		System.out.println("Here the employee ID is "+ Integer.toString(employeeId));
		return employeeId;
	}

	@Override
	public List<Employee> findAllEmployeesByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		List<Employee> employees = employeeDaoImpl.findAllEmployeesByEmployee(employee);
		
		
		return employees;
	}

	@Override
	public int updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		int countOfRecord = employeeDaoImpl.editEmployee(employee);
		return countOfRecord;
	}

	@Override
	@Transactional
	public int deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		int countOfRecord = employeeDaoImpl.deleteEmployee(employee);
		int result = userDaoImpl.deleteUserByEmployee(employee);
		
		return countOfRecord;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> allEmployees = employeeDaoImpl.getAllEmployees();
		return allEmployees;
	}

}
