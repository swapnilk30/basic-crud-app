package com.example.external.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class TestController {

	@PostMapping("/auth/login")
	public String login(String loginRequest) {
		
		return "";
	}
	
	
	
	
	
	

	@GetMapping("/employees")
	public Map<String, Object> getAllEmployeeDetails() {

		List<Map<String, Object>> employees = new ArrayList<>();

		employees.add(createEmployee(101, "Rahul Sharma", 28, "Software Engineer", "IT", 55000, "rahul@example.com",
				"9876543210", "Mumbai"));

		employees.add(createEmployee(102, "Priya Patil", 26, "HR Manager", "HR", 60000, "priya@example.com",
				"9876543211", "Pune"));

		employees.add(createEmployee(103, "Amit Verma", 32, "Project Manager", "IT", 85000, "amit@example.com",
				"9876543212", "Bangalore"));

		employees.add(createEmployee(104, "Sneha Joshi", 25, "QA Engineer", "Testing", 45000, "sneha@example.com",
				"9876543213", "Nashik"));

		employees.add(createEmployee(105, "Vikas Gupta", 30, "Business Analyst", "Finance", 70000, "vikas@example.com",
				"9876543214", "Delhi"));

		employees.add(createEmployee(106, "Neha Kulkarni", 27, "Java Developer", "IT", 65000, "neha@example.com",
				"9876543215", "Hyderabad"));

		employees.add(createEmployee(107, "Rohit Singh", 29, "UI Developer", "Development", 58000, "rohit@example.com",
				"9876543216", "Chennai"));

		employees.add(createEmployee(108, "Pooja Deshmukh", 31, "Team Lead", "IT", 95000, "pooja@example.com",
				"9876543217", "Pune"));

		employees.add(createEmployee(109, "Karan Mehta", 24, "Junior Developer", "Development", 35000,
				"karan@example.com", "9876543218", "Mumbai"));

		employees.add(createEmployee(110, "Anjali Shah", 28, "Data Analyst", "Analytics", 72000, "anjali@example.com",
				"9876543219", "Ahmedabad"));

		Map<String, Object> response = new HashMap<>();
		response.put("status", "success");
		response.put("totalEmployees", employees.size());
		response.put("employees", employees);

		return response;
	}

	private Map<String, Object> createEmployee(int id, String name, int age, String designation, String department,
			double salary, String email, String phone, String location) {

		Map<String, Object> employee = new LinkedHashMap<>();

		employee.put("id", id);
		employee.put("name", name);
		employee.put("age", age);
		employee.put("designation", designation);
		employee.put("department", department);
		employee.put("salary", salary);
		employee.put("email", email);
		employee.put("phone", phone);
		employee.put("location", location);

		return employee;
	}

}
