package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.entity.Role;

public interface RoleService {

	Optional<Role> findById(Long id);

	Optional<Role> findByName(String name);

	List<Role> findAll(); // Yeni eklenen method

	Role save(Role role);

	void deleteById(Long id);
}
