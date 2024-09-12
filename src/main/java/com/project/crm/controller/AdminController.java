package com.project.crm.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.crm.service.EmployeeService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private final EmployeeService employeeService;

	public AdminController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// Admin approves an employee
	@PostMapping("/approveEmployee/{employeeId}")
	public ResponseEntity<?> approveEmployee(@PathVariable UUID employeeId) {
		employeeService.approveEmployee(employeeId);
		return new ResponseEntity<>("Employee approved successfully!", HttpStatus.OK);
	}

	@GetMapping("/testMessage")
	public ResponseEntity<String> getTestMessage() {
		return new ResponseEntity<>("Hello, this is a test message from Admin!", HttpStatus.OK);
	}

}
