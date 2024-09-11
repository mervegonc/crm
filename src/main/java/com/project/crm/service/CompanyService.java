package com.project.crm.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.project.crm.dto.CompanySignupDTO;
import com.project.crm.entity.Company;

public interface CompanyService {

	Optional<Company> findById(UUID id);

	Optional<Company> findByCompanyCode(String companyCode);

	List<Company> findAllCompanies();

	Company save(Company company);

	Company updateCompany(UUID id, Company company);

	void deleteById(UUID id);

	void signupCompany(CompanySignupDTO companySignupDTO);

	boolean isCompanyExist(String companyCode);

}
