package com.project.crm.dto;

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
	private Long assignedToId;
	private String assignedToName;
}
