package com.project.crm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.crm.dto.TaskDTO;
import com.project.crm.entity.Task;
import com.project.crm.repository.UserRepository;

@Component
public class TaskMapper {

	@Autowired
	private UserRepository userRepository;

	public TaskDTO toDTO(Task task) {
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(task.getId()); // Bu Long kalabilir
		taskDTO.setTitle(task.getTitle());
		taskDTO.setDescription(task.getDescription());
		taskDTO.setStatus(task.getStatus());
		taskDTO.setAssignedToId(task.getAssignedTo().getUuid()); // Sadece userId UUID olacak
		taskDTO.setAssignedToName(task.getAssignedTo().getUsername());
		return taskDTO;
	}

	public Task toEntity(TaskDTO taskDTO) {
		Task task = new Task();
		task.setId(taskDTO.getId()); // taskId Long kalabilir
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setStatus(taskDTO.getStatus());
		task.setAssignedTo(userRepository.findById(taskDTO.getAssignedToId()) // userId UUID olacak
				.orElseThrow(() -> new RuntimeException("User not found")));
		return task;
	}

}
