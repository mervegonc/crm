package com.project.crm.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	private UUID id;

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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	private Status status;

	@Column(name = "company_code", nullable = false, unique = true)
	private String companyCode;

	@OneToMany(mappedBy = "company")
	private List<User> users;

	public String getCompanyCode() {
		return this.companyCode; // Doğrudan değişkeni döndür.
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public List<User> getUsers() {
		return this.getUsers();
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setFinancialInfo(FinancialInfo financialInfo2) {
		FinancialInfo financialInfo = new FinancialInfo();
		financialInfo.setAnnualRevenue(financialInfo2.getAnnualRevenue());
		financialInfo.setBudget(financialInfo2.getBudget());
		financialInfo.setTaxInfo(financialInfo2.getTaxInfo());
		this.financialInfo = financialInfo;
	}

	public void setStatus(Status status2) {
		Status status = new Status();
		status.setActive(status2.isActive());
		status.setOperationHours(status2.getOperationHours());
		status.setStatusHistory(status2.getStatusHistory());
		this.status = status;
	}

}
