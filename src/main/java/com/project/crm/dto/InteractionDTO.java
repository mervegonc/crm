package com.project.crm.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InteractionDTO {
	private Long id;
	private Long customerId;
	private UUID userUuid;
	private String interactionType;
	private String notes;
	private LocalDateTime date;
}
