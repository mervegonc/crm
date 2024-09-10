package com.project.crm.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.project.crm.entity.Task;

public interface TaskService {

	Optional<Task> findById(Long id);

	List<Task> findByAssignedToId(UUID uuid);

	List<Task> findAll();

	List<Task> findByStatus(String status);

	Task save(Task task);

	void deleteById(Long id);

}
