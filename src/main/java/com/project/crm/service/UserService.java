package com.project.crm.service;

import java.util.List;
import java.util.UUID;

import com.project.crm.dto.LoginDto;
import com.project.crm.dto.SignupDto;
import com.project.crm.dto.UserDTO;
import com.project.crm.entity.User;

public interface UserService {

	String login(LoginDto loginDto);

	void signup(SignupDto signupDto);

	void signupAndAssignRole(SignupDto signupDto, String roleName);

	boolean isUserExist(String username);

	public List<User> getAllUsers();

	public User saveOneUser(User newUser);

	String getUserIdByUsername(String usernameOrEmail);

	List<User> searchByUsername(String username);

	void resetPassword(User user, String newPassword);

	User findByUsernameOrEmail(String usernameOrEmail);

	// Tüm kullanıcıları listele
	List<UserDTO> getAllUser();

	// Yeni kullanıcı ekle
	UserDTO createUser(UserDTO userDTO);

	User getOneUserById(UUID userUuid);

	User updateOneUser(UUID userUuid, User newUser);

	User updateInfo(UUID userUuid, User newUserInfo);

	void deleteOneUser(UUID userUuid);

	User findById(UUID userUuid);

	UserDTO getUserById(UUID uuid);

	UserDTO updateUser(UUID uuid, UserDTO userDTO);

	void deleteUser(UUID uuid);

}
