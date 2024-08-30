package com.project.crm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordResetRequest {
	private String usernameOrEmail;
	private String passwordReminder;
	private String password;

	// Getters and setters
}