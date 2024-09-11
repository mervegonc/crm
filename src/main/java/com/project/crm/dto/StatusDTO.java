package com.project.crm.dto;

import lombok.Data;

@Data
public class StatusDTO {
	private boolean isActive;
	private String operationHours;
	private String statusHistory;
}
