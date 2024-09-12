package com.project.crm.dto;

import com.project.crm.entity.Department;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeSignupDTO {
	@NotNull(message = "Username is required")
	private String username;

	@NotNull(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotNull(message = "Password is required")
	private String password;

	@NotNull(message = "Company code is required")
	private String companyCode;

	@NotNull(message = "First name is required")
	private String firstName;

	@NotNull(message = "Last name is required")
	private String lastName;

	@NotNull(message = "Department is required")
	private Department department;
}
