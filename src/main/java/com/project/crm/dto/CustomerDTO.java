package com.project.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
}
