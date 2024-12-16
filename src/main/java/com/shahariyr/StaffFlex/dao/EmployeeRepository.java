package com.shahariyr.StaffFlex.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shahariyr.StaffFlex.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
