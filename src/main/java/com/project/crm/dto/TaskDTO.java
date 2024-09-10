package com.project.crm.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
	private Long id;
	private String title;
	private String description;
	private String status;
	private UUID assignedToId;
	private String assignedToName;
}
