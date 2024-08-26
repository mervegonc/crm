package com.project.crm.mapper;

import org.springframework.stereotype.Component;

import com.project.crm.dto.CompanyDTO;
import com.project.crm.entity.Company;

@Component
public class CompanyMapper {

	public CompanyDTO toDTO(Company company) {
		return new CompanyDTO(company.getId(), company.getName(), company.getCompanyCode());
	}

	public Company toEntity(CompanyDTO companyDTO) {
		Company company = new Company();
		company.setId(companyDTO.getId());
		company.setName(companyDTO.getName());
		company.setCompanyCode(companyDTO.getCompanyCode());
		return company;
	}
}
