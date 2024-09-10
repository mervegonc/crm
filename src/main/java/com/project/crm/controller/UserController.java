package com.project.crm.controller;

import java.util.List;
import java.util.UUID;

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

import com.project.crm.dto.UserDTO;
import com.project.crm.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Tüm kullanıcıları listele
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}

	// ID'ye göre kullanıcı getir
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable UUID uuid) {
		UserDTO user = userService.getUserById(uuid);
		return ResponseEntity.ok(user);
	}

	// Yeni kullanıcı ekle
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		UserDTO createdUser = userService.createUser(userDTO);
		return ResponseEntity.ok(createdUser);
	}

	// Kullanıcıyı güncelle
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable UUID uuid, @RequestBody UserDTO userDTO) {
		UserDTO updatedUser = userService.updateUser(uuid, userDTO);
		return ResponseEntity.ok(updatedUser);
	}

	// Kullanıcıyı sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID uuid) {
		userService.deleteUser(uuid);
		return ResponseEntity.noContent().build();
	}
}
