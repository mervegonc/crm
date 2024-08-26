package com.project.crm.mapper;

import org.springframework.stereotype.Component;

import com.project.crm.dto.RoleDTO;
import com.project.crm.entity.Role;

@Component
public class RoleMapper {

	public RoleDTO toDTO(Role role) {
		return new RoleDTO(role.getId(), role.getName());
	}

	public Role toEntity(RoleDTO roleDTO) {
		Role role = new Role();
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		return role;
	}
}
