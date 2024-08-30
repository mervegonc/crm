package com.project.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
	private String name;
	private String username;
	private String email;
	private String password;
	private String passwordReminder;
}
