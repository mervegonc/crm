package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.dto.LeadDTO;
import com.project.crm.entity.Lead;
import com.project.crm.mapper.LeadMapper;
import com.project.crm.repository.LeadRepository;
import com.project.crm.service.LeadService;

@Service
public class LeadServiceImpl implements LeadService {

	@Autowired
	private LeadRepository leadRepository;

	@Autowired
	private LeadMapper leadMapper;

	@Override
	public Optional<Lead> findById(Long id) {
		return leadRepository.findById(id);
	}

	@Override
	public Optional<Lead> findByEmail(String email) {
		return leadRepository.findByEmail(email);
	}

	@Override
	public List<Lead> findAll() {
		return leadRepository.findAll();
	}

	@Override
	public List<Lead> findByStatus(String status) {
		return leadRepository.findByStatus(status);
	}

	@Override
	public Lead save(Lead lead) {
		return leadRepository.save(lead);
	}

	@Override
	public void deleteById(Long id) {
		leadRepository.deleteById(id);
	}

	// Eksik metotlar
	@Override
	public List<LeadDTO> getAllLeads() {
		return leadRepository.findAll().stream().map(leadMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public LeadDTO getLeadById(Long id) {
		return leadRepository.findById(id).map(leadMapper::toDTO)
				.orElseThrow(() -> new RuntimeException("Lead not found"));
	}

	@Override
	public LeadDTO createLead(LeadDTO leadDTO) {
		Lead lead = leadMapper.toEntity(leadDTO);
		lead = leadRepository.save(lead);
		return leadMapper.toDTO(lead);
	}

	@Override
	public LeadDTO updateLead(Long id, LeadDTO leadDTO) {
		Lead existingLead = leadRepository.findById(id).orElseThrow(() -> new RuntimeException("Lead not found"));

		existingLead.setFirstName(leadDTO.getFirstName());
		existingLead.setLastName(leadDTO.getLastName());
		existingLead.setEmail(leadDTO.getEmail());
		existingLead.setPhone(leadDTO.getPhone());
		existingLead.setStatus(leadDTO.getStatus());
		existingLead.setSource(leadDTO.getSource());

		existingLead = leadRepository.save(existingLead);
		return leadMapper.toDTO(existingLead);
	}

	@Override
	public void deleteLead(Long id) {
		leadRepository.deleteById(id);
	}
}
