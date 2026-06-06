package com.shahariyr.StaffFlex.service;

import com.shahariyr.StaffFlex.entity.Employee;
import com.shahariyr.StaffFlex.entity.Payroll;
import com.shahariyr.StaffFlex.repository.EmployeeRepository;
import com.shahariyr.StaffFlex.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PayrollService {

    private PayrollRepository payrollRepository;
    private EmployeeRepository employeeRepository;

    public PayrollService(PayrollRepository payrollRepository, EmployeeRepository employeeRepository) {
        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
    }

    public Payroll generateMonthlyPayroll(Long employeeId, BigDecimal basic, BigDecimal allowances, BigDecimal deductions) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        BigDecimal netSalary = basic.add(allowances).subtract(deductions);

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setBasicSalary(basic);
        payroll.setAllowances(allowances);
        payroll.setDeductions(deductions);
        payroll.setNetSalary(netSalary);
        payroll.setPaymentDate(LocalDate.now());
        payroll.setMonth(LocalDate.now().getMonth().name());
        payroll.setYear(LocalDate.now().getYear());

        return payrollRepository.save(payroll);
    }
}
