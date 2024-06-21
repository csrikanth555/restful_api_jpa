package com.example.service;

import java.util.List;

import com.example.beans.Employee;

public interface EmployeeService {
	Employee addEmployee(Employee emp);

	Employee getEmployeeById(int empId);

	void updateEmployee(Employee emp);

	void deleteEmployeeById(int empId);

	List<Employee> getAllEmployees();
}