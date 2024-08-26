package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.entity.Interaction;
import com.project.crm.repository.InteractionRepository;
import com.project.crm.service.InteractionService;

@Service
public class InteractionServiceImpl implements InteractionService {

	@Autowired
	private InteractionRepository interactionRepository;

	@Override
	public Optional<Interaction> findById(Long id) {
		return interactionRepository.findById(id);
	}

	@Override
	public List<Interaction> findByCustomerId(Long customerId) {
		return interactionRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Interaction> findByUserId(Long userId) {
		return interactionRepository.findByUserId(userId);
	}

	@Override
	public List<Interaction> findByInteractionType(String interactionType) {
		return interactionRepository.findByInteractionType(interactionType);
	}

	@Override
	public List<Interaction> findAll() {
		return interactionRepository.findAll();
	}

	@Override
	public Interaction save(Interaction interaction) {
		return interactionRepository.save(interaction);
	}

	@Override
	public void deleteById(Long id) {
		interactionRepository.deleteById(id);
	}
}
