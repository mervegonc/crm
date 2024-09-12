package com.project.crm.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	@Override
	Optional<Employee> findById(UUID employeeId);
}
