package com.project.crm.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.crm.dto.EmployeeSignupDTO;
import com.project.crm.entity.Company;
import com.project.crm.entity.Employee;
import com.project.crm.entity.User;
import com.project.crm.repository.CompanyRepository;
import com.project.crm.repository.EmployeeRepository;
import com.project.crm.repository.RoleRepository;
import com.project.crm.service.EmployeeService;
import com.project.crm.service.UserService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final CompanyRepository companyRepository;
	private final EmployeeRepository employeeRepository;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Autowired
	public EmployeeServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository,
			UserService userService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.companyRepository = companyRepository;
		this.employeeRepository = employeeRepository;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public void signupEmployee(EmployeeSignupDTO employeeSignupDTO) {
		Optional<Company> companyOptional = companyRepository.findByCompanyCode(employeeSignupDTO.getCompanyCode());
		if (companyOptional.isEmpty()) {
			throw new RuntimeException("Invalid company code");
		}

		// Eğer departman null ise exception fırlat
		if (employeeSignupDTO.getDepartment() == null) {
			throw new RuntimeException("Department cannot be null");
		}

		// Kullanıcıyı kaydet ve ROLE_PENDING_EMPLOYEE rolü ata
		userService.signupAndAssignRole(employeeSignupDTO, "ROLE_PENDING_EMPLOYEE");

		User user = userService.findByUsername(employeeSignupDTO.getUsername());
		user.setCompany(companyOptional.get());
		userService.save(user);

		// Çalışanı veritabanına kaydet
		Employee employee = new Employee();
		employee.setFirstName(employeeSignupDTO.getFirstName());
		employee.setLastName(employeeSignupDTO.getLastName());
		employee.setCompany(companyOptional.get());
		employee.setDepartment(employeeSignupDTO.getDepartment());
		employee.setStatus("PENDING");
		employeeRepository.save(employee);
	}

	@Override
	public void approveEmployee(UUID employeeId) {
		// Find the employee in the EmployeeRepository
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		// Set the employee's status to APPROVED
		employee.setStatus("APPROVED");
		employeeRepository.save(employee);

		// Find the associated user by the employee's UUID
		User user = userService.findByEmployee(employee);

		// Change the role of the user to ROLE_EMPLOYEE
		userService.changeRole(user, "ROLE_EMPLOYEE");
	}

}
