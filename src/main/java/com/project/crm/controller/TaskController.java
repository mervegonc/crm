package com.project.crm.controller;

import java.util.List;
import java.util.Optional;

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

import com.project.crm.dto.TaskDTO;
import com.project.crm.entity.Task;
import com.project.crm.mapper.TaskMapper;
import com.project.crm.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskMapper taskMapper;

	// Tüm görevleri listele
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		List<TaskDTO> tasks = taskService.findAll().stream().map(taskMapper::toDTO).toList();
		return ResponseEntity.ok(tasks);
	}

	// ID'ye göre görev getir
	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
		return taskService.findById(id).map(taskMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Yeni görev ekle
	@PostMapping
	public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
		Task task = taskMapper.toEntity(taskDTO);
		Task createdTask = taskService.save(task);
		return ResponseEntity.ok(taskMapper.toDTO(createdTask));
	}

	// Görevi güncelle
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
		Optional<Task> optionalTask = taskService.findById(id);
		if (optionalTask.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Task task = taskMapper.toEntity(taskDTO);
		task.setId(id);
		Task updatedTask = taskService.save(task);
		return ResponseEntity.ok(taskMapper.toDTO(updatedTask));
	}

	// Görevi sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
