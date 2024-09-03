package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

	List<Campaign> findByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);

	List<Campaign> findBySuccessfulTrue();

	Optional<Campaign> findByName(String name);

	List<Campaign> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<Campaign> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
