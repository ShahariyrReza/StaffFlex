package com.shahariyr.StaffFlex.repository;

import com.shahariyr.StaffFlex.entity.Employee;
import com.shahariyr.StaffFlex.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByEmployee(Employee employee);
}
