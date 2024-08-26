package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByAssignedToId(Long userId);

	List<Task> findByStatus(String status);

	List<Task> findByOrderByDueDateAsc();

	List<Task> findByOrderByPriorityDesc();

	List<Task> findByCompletedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<Task> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
