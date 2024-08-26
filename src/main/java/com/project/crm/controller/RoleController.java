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

import com.project.crm.dto.RoleDTO;
import com.project.crm.entity.Role;
import com.project.crm.mapper.RoleMapper;
import com.project.crm.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleMapper roleMapper;

	// Tüm rolleri listele
	@GetMapping
	public ResponseEntity<List<RoleDTO>> getAllRoles() {
		List<RoleDTO> roles = roleService.findAll().stream().map(roleMapper::toDTO).toList();
		return ResponseEntity.ok(roles);
	}

	// ID'ye göre rol getir
	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
		return roleService.findById(id).map(roleMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Yeni rol ekle
	@PostMapping
	public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
		Role role = roleMapper.toEntity(roleDTO);
		Role createdRole = roleService.save(role);
		return ResponseEntity.ok(roleMapper.toDTO(createdRole));
	}

	// Rolü güncelle
	@PutMapping("/{id}")
	public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
		Optional<Role> optionalRole = roleService.findById(id);
		if (optionalRole.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Role role = roleMapper.toEntity(roleDTO);
		role.setId(id);
		Role updatedRole = roleService.save(role);
		return ResponseEntity.ok(roleMapper.toDTO(updatedRole));
	}

	// Rolü sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
		roleService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
