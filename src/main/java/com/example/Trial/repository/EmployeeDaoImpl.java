package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Trial.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Employee> employeeRowMapper = new RowMapper<Employee>() {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        	Employee employee = new Employee();
            employee.setEmployeeID(resultSet.getInt("employeeID"));
            employee.setFirstname(resultSet.getString("firstname"));
            employee.setLastname(resultSet.getString("lastname"));
            employee.setEmail(resultSet.getString("email"));
            employee.setPhone(resultSet.getString("phone"));
            employee.setAddress(resultSet.getString("address"));
            employee.setDoj(resultSet.getString("Doj"));
            employee.setEducation(resultSet.getString("education"));
            return employee;
        }
    };
	
	@Override
	@Transactional
	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		String sql = "Insert into employee(firstname,lastname,email,phone,address,roletitle,Doj,education) values(?,?,?,?,?,?,?,?)";
		Object[] args = {employee.getFirstname(),employee.getLastname(),employee.getEmail(),employee.getPhone(),employee.getAddress(),"employee",employee.getDoj(),employee.getEducation()};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		sql = "select * from employee where email = ?";
		Object[] args1 = {employee.getEmail()};
		
		Employee employee1 = jdbcTemplate.queryForObject(sql, employeeRowMapper,args1);
		System.out.println("EmployeeId = " + Integer.toString(employee1.getEmployeeID()) + "email = " + employee1.getEmail());
		
		return employee1.getEmployeeID();
	}

	@Override
	public List<Employee> findAllEmployeesByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String sql = "Select * from employee where email = ?";
		Object[] args = {employee.getEmail()};
		List<Employee> employees = jdbcTemplate.query(sql, employeeRowMapper,args);
		return employees;
	}

	@Override
	public int editEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String sql = "Update employee set firstname = ?,lastname = ?,phone = ?,address = ? where email = ?";
		Object[] args = {employee.getFirstname(),employee.getLastname(),employee.getPhone(),employee.getAddress(),employee.getEmail()};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}

	@Override
	public int deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String sql = "Delete from employee where email = ?";
		Object[] args = {employee.getEmail()};
		
		return jdbcTemplate.update(sql,args);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		
		String sql = "select * from employee ";
		
		List<Employee> allEmployees = jdbcTemplate.query(sql,employeeRowMapper);
		
		return allEmployees;
	}

}
