package com.project.crm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String status;
	private String source;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
