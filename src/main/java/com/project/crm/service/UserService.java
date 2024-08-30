package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.dto.LoginDto;
import com.project.crm.dto.SignupDto;
import com.project.crm.dto.UserDTO;
import com.project.crm.entity.User;

public interface UserService {

	Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	List<User> findAllActiveUsers();

	User save(User user);

	void deleteById(Long id);

	void activateUser(User user);

	// Yeni methodlar
	List<UserDTO> getAllUsers();

	UserDTO getUserById(Long id);

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(Long id, UserDTO userDTO);

	void deleteUser(Long id);

	String login(LoginDto loginDto);

	void signup(SignupDto signupDto);

	void signupAndAssignRole(SignupDto signupDto, String roleName);

	boolean isUserExist(String username);

	public User getOneUserById(Long userId);

}
