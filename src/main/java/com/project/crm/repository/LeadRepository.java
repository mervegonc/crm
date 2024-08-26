package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

	Optional<Lead> findByEmail(String email);

	List<Lead> findByStatus(String status);

	List<Lead> findBySource(String source);

	List<Lead> findByCampaignId(Long campaignId);

	List<Lead> findByOrderByCreatedAtDesc();

	List<Lead> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
