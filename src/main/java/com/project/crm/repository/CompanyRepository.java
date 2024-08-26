package com.project.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByCompanyCode(String companyCode);
}
