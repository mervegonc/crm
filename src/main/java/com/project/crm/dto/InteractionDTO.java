package com.project.crm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InteractionDTO {
	private Long id;
	private Long customerId;
	private Long userId;
	private String interactionType;
	private String notes;
	private LocalDateTime date;
}
