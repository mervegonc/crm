package com.project.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "financial_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "annual_revenue")
	private double annualRevenue;

	@Column(name = "budget")
	private double budget;

	@Column(name = "tax_info")
	private String taxInfo;

	@OneToOne(mappedBy = "financialInfo")
	private Company company;
}
