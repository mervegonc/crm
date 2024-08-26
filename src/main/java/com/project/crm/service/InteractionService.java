package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.entity.Interaction;

public interface InteractionService {

	Optional<Interaction> findById(Long id);

	List<Interaction> findByCustomerId(Long customerId);

	List<Interaction> findByUserId(Long userId);

	List<Interaction> findByInteractionType(String interactionType);

	List<Interaction> findAll();

	Interaction save(Interaction interaction);

	void deleteById(Long id);
}
