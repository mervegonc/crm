package com.project.crm.dto;

import lombok.Data;

@Data
public class CompanySignupDTO {
	private String name;
	private String phoneNumber;
	private String address;
	private String companyCode;
	private String email; // Company admin email
	private String password; // Company admin password
	private FinancialInfoDTO financialInfo;
	private StatusDTO status;
}