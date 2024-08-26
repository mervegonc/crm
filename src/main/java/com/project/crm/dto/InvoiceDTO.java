package com.project.crm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

	private Long id;
	private Long orderId;
	private LocalDateTime invoiceDate;
	private LocalDateTime dueDate;
	private String status;
	private Double totalAmount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
