package com.shahariyr.StaffFlex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shahariyr.StaffFlex.entity.Employee;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private List<Employee> theEmployees;

	@PostConstruct
	private void loadData() {

		// create employees
		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@gmail.com");
		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@hotmail.com");
		Employee emp3 = new Employee("Avani", "Gupta", "avani@yahoo.com");

		// create the list
		theEmployees = new ArrayList<>();

		// add to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "list-employees";
	}
	
}
