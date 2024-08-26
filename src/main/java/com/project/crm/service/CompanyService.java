package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.entity.Company;

public interface CompanyService {

	Optional<Company> findById(Long id);

	Optional<Company> findByCompanyCode(String companyCode);

	List<Company> findAllCompanies();

	Company save(Company company);

	Company updateCompany(Long id, Company company);

	void deleteById(Long id);
}
