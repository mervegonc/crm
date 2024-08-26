package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.entity.Company;
import com.project.crm.repository.CompanyRepository;
import com.project.crm.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Optional<Company> findById(Long id) {
		return companyRepository.findById(id);
	}

	@Override
	public Optional<Company> findByCompanyCode(String companyCode) {
		return companyRepository.findByCompanyCode(companyCode);
	}

	@Override
	public List<Company> findAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Long id, Company company) {
		Optional<Company> existingCompany = companyRepository.findById(id);
		if (existingCompany.isPresent()) {
			Company updatedCompany = existingCompany.get();
			updatedCompany.setName(company.getName());
			updatedCompany.setCompanyCode(company.getCompanyCode());
			updatedCompany.setUsers(company.getUsers());
			return companyRepository.save(updatedCompany);
		} else {
			return null; // Veya özel bir hata fırlatabilirsiniz
		}
	}

	@Override
	public void deleteById(Long id) {
		companyRepository.deleteById(id);
	}
}
