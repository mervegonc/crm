package com.project.crm.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.dto.LoginDto;
import com.project.crm.dto.SignupDto;
import com.project.crm.dto.UserDTO;
import com.project.crm.entity.User;
import com.project.crm.repository.UserRepository;
import com.project.crm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAllActiveUsers() {
		return userRepository.findByActiveTrue();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void activateUser(User user) {
		user.setActive(true);
		userRepository.save(user);
	}

	// Controller'da kullanılan yeni methodlar
	@Override
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream()
				.map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole()))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		user.setRole(userDTO.getRole());
		User savedUser = userRepository.save(user);
		return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole());
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		user.setRole(userDTO.getRole());
		User updatedUser = userRepository.save(user);
		return new UserDTO(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail(),
				updatedUser.getRole());
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void signup(SignupDto signupDto) {
		if (isUserExist(signupDto.getUsername())) {
			throw new RuntimeException("User already exists!");
		}

		User user = new User();
		user.setName(signupDto.getName());
		user.setUsername(signupDto.getUsername());
		user.setEmail(signupDto.getEmail());
		user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

		Role userRole = roleRepository.findByName("USER");
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		user.setRoles(roles);

		userRepository.save(user);
	}

	@Override
	public void signupAndAssignRole(SignupDto signupDto, String roleName) {
		User user = new User();
		user.setName(signupDto.getName());
		user.setUsername(signupDto.getUsername());
		user.setEmail(signupDto.getEmail());
		user.setPasswordReminder(signupDto.getPasswordReminder());
		user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

		Role userRole = roleRepository.findByName(roleName);
		if (userRole == null) {
			throw new RuntimeException("Role not found: " + roleName);
		}

		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		user.setRoles(roles);

		userRepository.save(user);
	}

	@Override
	public boolean isUserExist(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public String login(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
