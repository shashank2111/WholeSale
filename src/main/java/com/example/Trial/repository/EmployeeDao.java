package com.example.Trial.repository;

import java.util.List;

import com.example.Trial.model.Employee;

public interface EmployeeDao {
	public int addEmployee(Employee employee);
	public List<Employee> findAllEmployeesByEmployee(Employee employee);
	public int editEmployee(Employee employee);
	public int deleteEmployee(Employee employee);
}
