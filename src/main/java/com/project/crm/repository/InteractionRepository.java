package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {

	List<Interaction> findByCustomerId(Long customerId);

	List<Interaction> findByUserId(Long userId);

	List<Interaction> findByInteractionType(String interactionType);

	List<Interaction> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<Interaction> findByOrderByDateDesc();

	List<Interaction> findByCustomerIdOrderByDateDesc(Long customerId);
}
