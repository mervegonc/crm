package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.dto.LeadDTO;
import com.project.crm.entity.Lead;

public interface LeadService {

	Optional<Lead> findById(Long id);

	Optional<Lead> findByEmail(String email);

	List<Lead> findAll();

	List<Lead> findByStatus(String status);

	Lead save(Lead lead);

	void deleteById(Long id);

	// Eksik metotlar
	List<LeadDTO> getAllLeads();

	LeadDTO getLeadById(Long id);

	LeadDTO createLead(LeadDTO leadDTO);

	LeadDTO updateLead(Long id, LeadDTO leadDTO);

	void deleteLead(Long id);
}
