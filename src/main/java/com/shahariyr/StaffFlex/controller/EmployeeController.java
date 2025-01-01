package com.shahariyr.StaffFlex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shahariyr.StaffFlex.entity.Employee;
import com.shahariyr.StaffFlex.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	//Constructor Injection of employeeService
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	

	// add mapping to get all employee data.
	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();
		theModel.addAttribute("employees", theEmployees); // th:each="tempEmployee : ${employees}"

		return "employee/list-employees";
	}
	
	
	//add mapping to show employee input form.
	@GetMapping("/form")
	public String showForm(Model theModel) {
		
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee); //th:object ="${employee}"
		
		return "employee/addEmployee";
	}
	
	
	@PostMapping("/save")
	private String saveEmployee(@ModelAttribute("employee") Employee theEmployee) /* th:object="${employee}" */ {
		
		employeeService.save(theEmployee);
		
		return "redirect:/employees/list";
	}
	

}








