package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.dto.CompanySignupDTO;
import com.project.crm.entity.Company;
import com.project.crm.entity.FinancialInfo;
import com.project.crm.entity.Status;
import com.project.crm.repository.CompanyRepository;
import com.project.crm.repository.FinancialInfoRepository;
import com.project.crm.repository.StatusRepository;
import com.project.crm.repository.UserRepository;
import com.project.crm.service.CompanyService;
import com.project.crm.service.UserService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;
	private final UserService userService;
	private final UserRepository userRepository;
	private final StatusRepository statusRepository;
	private final FinancialInfoRepository financialInfoRepository;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository, UserService userService,
			UserRepository userRepository, StatusRepository statusRepository,
			FinancialInfoRepository financialInfoRepository) {
		this.companyRepository = companyRepository;
		this.userService = userService;
		this.userRepository = userRepository;
		this.statusRepository = statusRepository;
		this.financialInfoRepository = financialInfoRepository;
	}

	@Override
	public void signupCompany(CompanySignupDTO companySignupDTO) {
		// Şirket kodu mevcutsa hata fırlat
		if (companyRepository.existsByCompanyCode(companySignupDTO.getCompanyCode())) {
			throw new RuntimeException("Company already exists");
		}

		// Status kaydetme
		Status status = new Status();
		status.setActive(companySignupDTO.getStatus().isActive());
		status.setOperationHours(companySignupDTO.getStatus().getOperationHours());
		status.setStatusHistory(companySignupDTO.getStatus().getStatusHistory());
		statusRepository.save(status); // Status önce kaydediliyor

		// FinancialInfo kaydetme
		FinancialInfo financialInfo = new FinancialInfo();
		financialInfo.setAnnualRevenue(companySignupDTO.getFinancialInfo().getAnnualRevenue());
		financialInfo.setBudget(companySignupDTO.getFinancialInfo().getBudget());
		financialInfo.setTaxInfo(companySignupDTO.getFinancialInfo().getTaxInfo());
		financialInfoRepository.save(financialInfo); // FinancialInfo önce kaydediliyor

		// Yeni şirket oluştur ve kaydet
		Company company = new Company();
		company.setName(companySignupDTO.getName());
		company.setPhoneNumber(companySignupDTO.getPhoneNumber());
		company.setAddress(companySignupDTO.getAddress());
		company.setCompanyCode(companySignupDTO.getCompanyCode());
		company.setFinancialInfo(financialInfo); // Kaydedilen financialInfo ekleniyor
		company.setStatus(status); // Kaydedilen status ekleniyor

		companyRepository.save(company); // Şirket kaydediliyor
	}

	@Override
	public boolean isCompanyExist(String companyCode) {
		return companyRepository.existsByCompanyCode(companyCode);
	}

	@Override
	public Optional<Company> findById(UUID id) {
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
	public Company updateCompany(UUID id, Company company) {
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
	public void deleteById(UUID id) {
		companyRepository.deleteById(id);
	}

}
