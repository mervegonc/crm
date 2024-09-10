package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Task;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t FROM Task t WHERE t.assignedTo.uuid = :uuid")
	List<Task> findByAssignedToUuid(@Param("uuid") UUID uuid);

	List<Task> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<Task> findByStatus(String status);

	List<Task> findByOrderByDueDateAsc();

	List<Task> findByOrderByPriorityDesc();

	List<Task> findByCompletedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

}
