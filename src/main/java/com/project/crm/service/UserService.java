package com.project.crm.service;

import java.util.List;

import com.project.crm.dto.LoginDto;
import com.project.crm.dto.SignupDto;
import com.project.crm.dto.UserDTO;
import com.project.crm.entity.User;

public interface UserService {

	String login(LoginDto loginDto);

	void signup(SignupDto signupDto);

	void signupAndAssignRole(SignupDto signupDto, String roleName);

	boolean isUserExist(String username);

	public User getOneUserById(Long userId);

	public List<User> getAllUsers();

	public User saveOneUser(User newUser);

	public User updateOneUser(Long userId, User newUser);

	User updateInfo(Long userId, User newUserInfo);

	public void deleteOneUser(Long userId);

	Long getUserIdByUsername(String usernameOrEmail);

	List<User> searchByUsername(String username);

	void resetPassword(User user, String newPassword);

	User findByUsernameOrEmail(String usernameOrEmail);

	User findById(Long userId);

	// Tüm kullanıcıları listele
	List<UserDTO> getAllUser();

	// ID'ye göre kullanıcı getir
	UserDTO getUserById(Long id);

	// Yeni kullanıcı ekle
	UserDTO createUser(UserDTO userDTO);

	// Kullanıcıyı güncelle
	UserDTO updateUser(Long id, UserDTO userDTO);

	// Kullanıcıyı sil
	void deleteUser(Long id);

}
