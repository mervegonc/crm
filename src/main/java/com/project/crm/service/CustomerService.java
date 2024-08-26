package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.dto.CustomerDTO;
import com.project.crm.entity.Customer;

public interface CustomerService {

	Optional<Customer> findById(Long id);

	Optional<Customer> findByEmail(String email);

	List<Customer> findAll();

	Customer save(Customer customer);

	void deleteById(Long id);

	List<CustomerDTO> getAllCustomers();

	CustomerDTO getCustomerById(Long id);

	CustomerDTO createCustomer(CustomerDTO customerDTO);

	CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

	void deleteCustomer(Long id);
}
