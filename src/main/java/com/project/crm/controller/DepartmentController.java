package com.project.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.crm.dto.DepartmentDTO;
import com.project.crm.service.DepartmentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	@PostMapping("/create")
	public ResponseEntity<String> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
		departmentService.addDepartment(departmentDTO);
		return new ResponseEntity<>("Department added successfully", HttpStatus.CREATED);
	}
}
