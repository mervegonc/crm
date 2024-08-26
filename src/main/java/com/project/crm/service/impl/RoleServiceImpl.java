package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.entity.Role;
import com.project.crm.repository.RoleRepository;
import com.project.crm.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Optional<Role> findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public List<Role> findAll() { // Yeni eklenen method
		return roleRepository.findAll();
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);
	}
}
