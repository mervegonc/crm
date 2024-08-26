package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);

	List<Customer> findByLastName(String lastName);

	Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);

	List<Customer> findByActiveTrue();

	List<Customer> findBySegment(String segment);

	List<Customer> findByOrderByCreatedAtDesc();

	List<Customer> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
