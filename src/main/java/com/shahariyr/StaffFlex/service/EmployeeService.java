package com.shahariyr.StaffFlex.service;

import com.shahariyr.StaffFlex.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> findAll();

    EmployeeDto findById(Long id);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteById(Long id);
}
