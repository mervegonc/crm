package com.project.crm.mapper;

import org.springframework.stereotype.Component;

import com.project.crm.dto.LeadDTO;
import com.project.crm.entity.Lead;

@Component
public class LeadMapper {

	public LeadDTO toDTO(Lead lead) {
		return new LeadDTO(lead.getId(), lead.getFirstName(), lead.getLastName(), lead.getEmail(), lead.getPhone(),
				lead.getStatus(), lead.getSource(), lead.getCreatedAt(), lead.getUpdatedAt());
	}

	public Lead toEntity(LeadDTO leadDTO) {
		Lead lead = new Lead();
		lead.setId(leadDTO.getId());
		lead.setFirstName(leadDTO.getFirstName());
		lead.setLastName(leadDTO.getLastName());
		lead.setEmail(leadDTO.getEmail());
		lead.setPhone(leadDTO.getPhone());
		lead.setStatus(leadDTO.getStatus());
		lead.setSource(leadDTO.getSource());
		lead.setCreatedAt(leadDTO.getCreatedAt());
		lead.setUpdatedAt(leadDTO.getUpdatedAt());
		return lead;
	}
}
