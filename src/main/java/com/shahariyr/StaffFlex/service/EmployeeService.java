package com.shahariyr.StaffFlex.service;

import java.util.List;

import com.shahariyr.StaffFlex.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findById(int theId);

	void save(Employee theEmployee);

	void deleteById(int theId);
}
