package com.project.crm.mapper;

import org.springframework.stereotype.Component;

import com.project.crm.dto.CustomerDTO;
import com.project.crm.entity.Customer;

@Component
public class CustomerMapper {

	public CustomerDTO toDTO(Customer customer) {
		// Customer entity'den CustomerDTO'ya dönüşüm işlemi
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setFirstName(customer.getFirstName()); // firstName alanı
		customerDTO.setLastName(customer.getLastName()); // lastName alanı
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setPhone(customer.getPhone());
		customerDTO.setAddress(customer.getAddress());
		// Diğer alanları da ekleyin
		return customerDTO;
	}

	public Customer toEntity(CustomerDTO customerDTO) {
		// CustomerDTO'dan Customer entity'ye dönüşüm işlemi
		Customer customer = new Customer();
		customer.setId(customerDTO.getId());
		customer.setFirstName(customerDTO.getFirstName()); // firstName alanı
		customer.setLastName(customerDTO.getLastName()); // lastName alanı
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());
		customer.setAddress(customerDTO.getAddress());
		// Diğer alanları da ekleyin
		return customer;
	}

	public void updateEntityFromDTO(CustomerDTO customerDTO, Customer customer) {
		// Mevcut Customer entity'yi CustomerDTO verileriyle güncelleyin
		customer.setFirstName(customerDTO.getFirstName()); // firstName alanı
		customer.setLastName(customerDTO.getLastName()); // lastName alanı
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());
		customer.setAddress(customerDTO.getAddress());
		// Diğer alanları da ekleyin
	}
}
