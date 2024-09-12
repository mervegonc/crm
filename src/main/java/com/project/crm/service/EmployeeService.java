package com.project.crm.service;

import java.util.UUID;

import com.project.crm.dto.EmployeeSignupDTO;

public interface EmployeeService {

	void signupEmployee(EmployeeSignupDTO employeeSignupDTO);

	void approveEmployee(UUID employeeId);
}
