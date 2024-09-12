package com.project.crm.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.crm.dto.EmployeeSignupDTO;
import com.project.crm.dto.LoginDto;
import com.project.crm.dto.SignupDto;
import com.project.crm.dto.UserDTO;
import com.project.crm.entity.Employee;
import com.project.crm.entity.Role;
import com.project.crm.entity.User;
import com.project.crm.entity.UserRole;
import com.project.crm.jwt.JwtTokenProvider;
import com.project.crm.repository.EmployeeRepository;
import com.project.crm.repository.RoleRepository;
import com.project.crm.repository.UserRepository;
import com.project.crm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final EmployeeRepository employeeRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, EmployeeRepository employeeRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public String login(LoginDto loginDto) {
		Optional<User> userOptional = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),
				loginDto.getUsernameOrEmail());

		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("This username or email doesn't exist.");
		}

		User user = userOptional.get();

		// Check if user has 'ROLE_PENDING_EMPLOYEE'
		if (user.getRoles().contains(roleRepository.findByName("ROLE_PENDING_EMPLOYEE").get())) {
			throw new RuntimeException("Your account has not been approved by an admin yet.");
		}

		// Check password
		if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid username or password.");
		}

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null,
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList()));

		return jwtTokenProvider.generateToken(authentication);
	}

	@Override
	public void approveEmployee(UUID employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));
		employee.setStatus("APPROVED"); // Change status to APPROVED
		employeeRepository.save(employee);
	}

	@Override
	public String getUserIdByUsername(String usernameOrEmail) {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return user.getUuid().toString(); // Ensure you convert the UUID to a String
	}

	@Override
	public boolean isUserExist(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	@Transactional
	public void signupAndAssignRole(SignupDto signupDto, String roleName) {
		User user = new User();
		user.setUsername(signupDto.getUsername());
		user.setEmail(signupDto.getEmail());
		user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		user.setActive(true);

		Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));

		Set<Role> roles = Set.of(role);
		user.setRoles(Optional.of(roles)); // Assigning the roles correctly
		userRepository.save(user);
	}

	@Override
	public User findByUsernameOrEmail(String usernameOrEmail) {
		return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElse(null);
	}

	@Override
	@Transactional
	public void resetPassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	@Override
	public void signup(SignupDto signupDto) {
		User user = new User();
		user.setUsername(signupDto.getUsername());
		user.setEmail(signupDto.getEmail());
		user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		user.setActive(true);

		Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));

		user.setRoles(Optional.ofNullable(Set.of(role)));
		userRepository.save(user);
	}

	@Override
	public User getOneUserById(UUID userUuid) {
		return userRepository.findById(userUuid).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}

	@Override
	public User updateOneUser(UUID userUuid, User newUser) {
		User existingUser = userRepository.findById(userUuid).orElseThrow(() -> new RuntimeException("User not found"));
		existingUser.setUsername(newUser.getUsername());
		existingUser.setEmail(newUser.getEmail());
		existingUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		existingUser.setActive(newUser.isActive());
		return userRepository.save(existingUser);
	}

	@Override
	public User updateInfo(UUID userUuid, User newUserInfo) {
		User existingUser = userRepository.findById(userUuid).orElseThrow(() -> new RuntimeException("User not found"));
		existingUser.setUsername(newUserInfo.getUsername());
		existingUser.setEmail(newUserInfo.getEmail());
		existingUser.setPassword(passwordEncoder.encode(newUserInfo.getPassword()));
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteOneUser(UUID userUuid) {
		userRepository.deleteById(userUuid);
	}

	@Override
	public List<User> searchByUsername(String username) {
		return userRepository.findByUsernameContaining(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findById(UUID userUuid) {
		return userRepository.findById(userUuid).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public List<UserDTO> getAllUser() {
		return userRepository.findAll().stream()
				.map(user -> new UserDTO(user.getUuid(), user.getUsername(), user.getEmail(),
						user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet())))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(UUID uuid) {
		User user = userRepository.findById(uuid)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + uuid));
		return new UserDTO(user.getUuid(), user.getUsername(), user.getEmail(),
				user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()));
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		// Roller eklenmeli (şu an için basitçe boş bırakıyoruz)
		// user.setRoles(...);

		User savedUser = userRepository.save(user);
		return new UserDTO(savedUser.getUuid(), savedUser.getUsername(), savedUser.getEmail(),
				savedUser.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()));
	}

	@Override
	public UserDTO updateUser(UUID uuid, UserDTO userDTO) {
		User user = userRepository.findById(uuid)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + uuid));
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		// Roller güncellenmeli
		// user.setRoles(...);

		User updatedUser = userRepository.save(user);
		return new UserDTO(updatedUser.getUuid(), updatedUser.getUsername(), updatedUser.getEmail(),
				updatedUser.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()));
	}

	@Override
	public void deleteUser(UUID uuid) {
		if (!userRepository.existsById(uuid)) {
			throw new RuntimeException("User not found with uuid: " + uuid);
		}
		userRepository.deleteById(uuid);
	}

	@Override
	public void signupAndAssignRole(String username, String email, String password, String roleName) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setActive(true);

		// Find and assign role
		Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found!"));
		user.setRoles(Optional.of(Collections.singleton(role))); // No Optional here

		userRepository.save(user);
	}

	@Override
	public void signupAndAssignRole(EmployeeSignupDTO signupDto, String roleName) {
		// Create a new user object
		User user = new User();
		user.setUsername(signupDto.getUsername());
		user.setEmail(signupDto.getEmail());
		user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		user.setActive(true);

		// Fetch the role from the database and create the UserRole
		Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found!"));
		UserRole userRole = new UserRole(user, role);

		// Assign the role to the user
		user.setUserRoles((Set<UserRole>) List.of(userRole));

		// Save the user to the repository
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeRole(User user, String string) {
		// TODO Auto-generated method stub

	}

}