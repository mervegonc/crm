package com.project.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.crm.dto.CustomerDTO;
import com.project.crm.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// Tüm müşterileri listele
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		List<CustomerDTO> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}

	// ID'ye göre müşteri getir
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
		CustomerDTO customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(customer);
	}

	// Yeni müşteri ekle
	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
		CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
		return ResponseEntity.ok(createdCustomer);
	}

	// Müşteriyi güncelle
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
		return ResponseEntity.ok(updatedCustomer);
	}

	// Müşteriyi sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}
}
