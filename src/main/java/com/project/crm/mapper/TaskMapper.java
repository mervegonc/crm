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
		taskDTO.setId(task.getId());
		taskDTO.setTitle(task.getTitle());
		taskDTO.setDescription(task.getDescription());
		taskDTO.setStatus(task.getStatus());
		taskDTO.setAssignedToId(task.getAssignedTo().getId());
		taskDTO.setAssignedToName(task.getAssignedTo().getUsername());
		return taskDTO;
	}

	public Task toEntity(TaskDTO taskDTO) {
		Task task = new Task();
		task.setId(taskDTO.getId());
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setStatus(taskDTO.getStatus());
		task.setAssignedTo(userRepository.findById(taskDTO.getAssignedToId()).orElse(null));
		return task;
	}
}
