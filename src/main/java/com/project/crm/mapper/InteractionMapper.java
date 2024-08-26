package com.project.crm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.crm.dto.InteractionDTO;
import com.project.crm.entity.Interaction;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.repository.UserRepository;

@Component
public class InteractionMapper {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	public InteractionDTO toDTO(Interaction interaction) {
		return new InteractionDTO(interaction.getId(), interaction.getCustomer().getId(), interaction.getUser().getId(),
				interaction.getInteractionType(), interaction.getNotes(), interaction.getDate());
	}

	public Interaction toEntity(InteractionDTO interactionDTO) {
		Interaction interaction = new Interaction();
		interaction.setId(interactionDTO.getId());
		interaction.setCustomer(customerRepository.findById(interactionDTO.getCustomerId()).orElse(null));
		interaction.setUser(userRepository.findById(interactionDTO.getUserId()).orElse(null));
		interaction.setInteractionType(interactionDTO.getInteractionType());
		interaction.setNotes(interactionDTO.getNotes());
		interaction.setDate(interactionDTO.getDate());
		return interaction;
	}
}
