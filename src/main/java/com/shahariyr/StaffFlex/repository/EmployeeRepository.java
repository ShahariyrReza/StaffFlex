package com.shahariyr.StaffFlex.repository;

import com.shahariyr.StaffFlex.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
