package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.dto.CustomerDTO;
import com.project.crm.entity.Customer;
import com.project.crm.exception.ResourceNotFoundException;
import com.project.crm.mapper.CustomerMapper;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public Optional<Customer> findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

	// Eksik olan yöntemlerin implementasyonu
	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll().stream().map(customerMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
		return customerMapper.toDTO(customer);
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		Customer customer = customerMapper.toEntity(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		return customerMapper.toDTO(savedCustomer);
	}

	@Override
	public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
		customerMapper.updateEntityFromDTO(customerDTO, customer);
		Customer updatedCustomer = customerRepository.save(customer);
		return customerMapper.toDTO(updatedCustomer);
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
}
