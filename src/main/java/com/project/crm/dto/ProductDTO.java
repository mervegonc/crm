package com.project.crm.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private Long id;
	private String productName;
	private String description;
	private double price;
}
