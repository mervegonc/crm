package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.entity.Task;
import com.project.crm.repository.TaskRepository;
import com.project.crm.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Optional<Task> findById(Long id) {
		return taskRepository.findById(id);
	}

	@Override
	public List<Task> findByAssignedToId(UUID uuid) {
		return taskRepository.findByAssignedToUuid(uuid);
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> findByStatus(String status) {
		return taskRepository.findByStatus(status);
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public void deleteById(Long id) {
		taskRepository.deleteById(id);
	}
}
