package com.project.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.crm.dto.CompanySignupDTO;
import com.project.crm.dto.EmployeeSignupDTO;
import com.project.crm.dto.LoginDto;
import com.project.crm.dto.SignupDto;
import com.project.crm.dto.request.PasswordResetRequest;
import com.project.crm.dto.response.GenericResponse;
import com.project.crm.dto.response.JwtAuthResponse;
import com.project.crm.dto.response.JwtSignupResponse;
import com.project.crm.entity.User;
import com.project.crm.jwt.JwtTokenProvider;
import com.project.crm.repository.EmployeeRepository;
import com.project.crm.service.CompanyService;
import com.project.crm.service.EmployeeService;
import com.project.crm.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserService userService;
	private final JwtTokenProvider jwtTokenProvider;
	private final CompanyService companyService;
	private final EmployeeService employeeService;
	private final EmployeeRepository employeeRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		try {
			String token = userService.login(loginDto);
			String userUuid = userService.getUserIdByUsername(loginDto.getUsernameOrEmail());
			JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
			jwtAuthResponse.setAccessToken(token);
			jwtAuthResponse.setUserUuid(userUuid);
			return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			return new ResponseEntity<>("This username or email doesn't exist, please sign up first.",
					HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid username or password.", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/signup/company")
	public ResponseEntity<JwtSignupResponse> companySignup(@RequestBody CompanySignupDTO companySignupDto) {
		try {
			companyService.signupCompany(companySignupDto);
			JwtSignupResponse jwtSignupResponse = new JwtSignupResponse();
			jwtSignupResponse.setMessage("Company registered successfully!");
			return new ResponseEntity<>(jwtSignupResponse, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			JwtSignupResponse response = new JwtSignupResponse();
			response.setMessage(e.getMessage()); // Return specific message in case of conflict
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<JwtSignupResponse> signup(@RequestBody SignupDto signupDto) {
		boolean isUserExist = userService.isUserExist(signupDto.getUsername());

		if (isUserExist) {
			JwtSignupResponse response = new JwtSignupResponse();
			response.setMessage("User already exists");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

		userService.signupAndAssignRole(signupDto, "ROLE_USER");

		JwtSignupResponse jwtSignupResponse = new JwtSignupResponse();
		jwtSignupResponse.setMessage("User registered successfully!");
		return new ResponseEntity<>(jwtSignupResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signup/employee")
	public ResponseEntity<?> employeeSignup(@RequestBody EmployeeSignupDTO signupDto) {
		try {
			employeeService.signupEmployee(signupDto);
			return new ResponseEntity<>("Employee registered successfully", HttpStatus.CREATED);
		} catch (RuntimeException e) {
			// Daha fazla bilgi için log ekle
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// Diğer hataları da yakala
			e.printStackTrace();
			return new ResponseEntity<>("Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<GenericResponse> forgotPassword(@RequestBody PasswordResetRequest request) {
		User user = userService.findByUsernameOrEmail(request.getUsernameOrEmail());

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		if (!user.getPasswordReminder().equals(request.getPasswordReminder())) {
			return new ResponseEntity<>(new GenericResponse("Incorrect password reminder"), HttpStatus.BAD_REQUEST);
		}

		userService.resetPassword(user, request.getPassword());

		return new ResponseEntity<>(new GenericResponse("Password reset successfully"), HttpStatus.OK);
	}
}
