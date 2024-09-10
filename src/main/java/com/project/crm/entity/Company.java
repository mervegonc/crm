package com.project.crm.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Employee> employees;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Department> departments;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "financial_info_id", referencedColumnName = "id")
	private FinancialInfo financialInfo;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID();
	}
}
