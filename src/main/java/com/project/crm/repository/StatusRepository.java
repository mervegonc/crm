package com.project.crm.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, UUID> {
	// Additional query methods can be defined here if needed
}
