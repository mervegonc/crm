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

import com.project.crm.dto.InteractionDTO;
import com.project.crm.entity.Interaction;
import com.project.crm.mapper.InteractionMapper;
import com.project.crm.service.InteractionService;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

	@Autowired
	private InteractionService interactionService;

	@Autowired
	private InteractionMapper interactionMapper;

	// Tüm etkileşimleri listele
	@GetMapping
	public ResponseEntity<List<InteractionDTO>> getAllInteractions() {
		List<InteractionDTO> interactions = interactionService.findAll().stream().map(interactionMapper::toDTO)
				.toList();
		return ResponseEntity.ok(interactions);
	}

	// ID'ye göre etkileşim getir
	@GetMapping("/{id}")
	public ResponseEntity<InteractionDTO> getInteractionById(@PathVariable Long id) {
		return interactionService.findById(id).map(interactionMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Yeni etkileşim ekle
	@PostMapping
	public ResponseEntity<InteractionDTO> createInteraction(@RequestBody InteractionDTO interactionDTO) {
		Interaction interaction = interactionMapper.toEntity(interactionDTO);
		Interaction createdInteraction = interactionService.save(interaction);
		return ResponseEntity.ok(interactionMapper.toDTO(createdInteraction));
	}

	// Etkileşimi güncelle
	@PutMapping("/{id}")
	public ResponseEntity<InteractionDTO> updateInteraction(@PathVariable Long id,
			@RequestBody InteractionDTO interactionDTO) {
		Interaction interaction = interactionMapper.toEntity(interactionDTO);
		interaction.setId(id);
		Interaction updatedInteraction = interactionService.save(interaction);
		return ResponseEntity.ok(interactionMapper.toDTO(updatedInteraction));
	}

	// Etkileşimi sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInteraction(@PathVariable Long id) {
		interactionService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
