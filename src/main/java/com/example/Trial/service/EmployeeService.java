package com.example.Trial.service;

import java.util.List;

import com.example.Trial.model.Employee;

public interface EmployeeService {
	public int addEmployee(Employee employee);
	public List<Employee> findAllEmployeesByEmployee(Employee employee);
	public int updateEmployee(Employee employee);
	public int deleteEmployee(Employee employee);
}
