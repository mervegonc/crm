package com.project.crm.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.crm.dto.CompanyDTO;
import com.project.crm.entity.Company;
import com.project.crm.mapper.CompanyMapper;
import com.project.crm.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyMapper companyMapper;

	// Tüm şirketleri listele
	@GetMapping
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		List<CompanyDTO> companies = companyService.findAllCompanies().stream().map(companyMapper::toDTO).toList();
		return ResponseEntity.ok(companies);
	}

	// ID'ye göre şirket getir
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable UUID id) {
		return companyService.findById(id).map(companyMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Yeni şirket ekle
	@PostMapping
	public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
		Company company = companyMapper.toEntity(companyDTO);
		Company createdCompany = companyService.save(company);
		return ResponseEntity.ok(companyMapper.toDTO(createdCompany));
	}

	// Şirketi güncelle
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable UUID id, @RequestBody CompanyDTO companyDTO) {
		Company company = companyMapper.toEntity(companyDTO);
		Company updatedCompany = companyService.updateCompany(id, company);
		if (updatedCompany != null) {
			return ResponseEntity.ok(companyMapper.toDTO(updatedCompany));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Şirketi sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompany(@PathVariable UUID id) {
		companyService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
