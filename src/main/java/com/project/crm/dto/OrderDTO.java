package com.project.crm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Long id;
	private String product;
	private int quantity;
	private double price;
	private String status;
	private double totalPrice;
	private LocalDateTime orderDate;

}
