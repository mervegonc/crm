package com.project.crm.controller;

import java.util.List;

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

import com.project.crm.dto.LeadDTO;
import com.project.crm.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	private LeadService leadService;

	// Tüm potansiyel müşterileri listele
	@GetMapping
	public ResponseEntity<List<LeadDTO>> getAllLeads() {
		List<LeadDTO> leads = leadService.getAllLeads();
		return ResponseEntity.ok(leads);
	}

	// ID'ye göre potansiyel müşteri getir
	@GetMapping("/{id}")
	public ResponseEntity<LeadDTO> getLeadById(@PathVariable Long id) {
		LeadDTO lead = leadService.getLeadById(id);
		return ResponseEntity.ok(lead);
	}

	// Yeni potansiyel müşteri ekle
	@PostMapping
	public ResponseEntity<LeadDTO> createLead(@RequestBody LeadDTO leadDTO) {
		LeadDTO createdLead = leadService.createLead(leadDTO);
		return ResponseEntity.ok(createdLead);
	}

	// Potansiyel müşteriyi güncelle
	@PutMapping("/{id}")
	public ResponseEntity<LeadDTO> updateLead(@PathVariable Long id, @RequestBody LeadDTO leadDTO) {
		LeadDTO updatedLead = leadService.updateLead(id, leadDTO);
		return ResponseEntity.ok(updatedLead);
	}

	// Potansiyel müşteriyi sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
		leadService.deleteLead(id);
		return ResponseEntity.noContent().build();
	}
}
