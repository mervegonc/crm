package com.project.crm.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String firstName;
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@Column(name = "status", nullable = false)
	private String status = "PENDING"; // Default status to 'PENDING'

}
