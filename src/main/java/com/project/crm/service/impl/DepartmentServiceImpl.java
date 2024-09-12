package com.project.crm.service.impl;

import org.springframework.stereotype.Service;

import com.project.crm.dto.DepartmentDTO;
import com.project.crm.entity.Department;
import com.project.crm.repository.DepartmentRepository;
import com.project.crm.service.DepartmentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Override
	public void addDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department();
		department.setName(departmentDTO.getName());
		departmentRepository.save(department);
	}
}
