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

import com.project.crm.dto.InvoiceDTO;
import com.project.crm.service.InvoiceService;

import jakarta.persistence.Column;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@Column(name = "customer_id")
	private Long customerId;

	// Tüm faturaları listele
	@GetMapping
	public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
		List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
		return ResponseEntity.ok(invoices);
	}

	// ID'ye göre fatura getir
	@GetMapping("/{id}")
	public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
		InvoiceDTO invoice = invoiceService.getInvoiceById(id);
		return ResponseEntity.ok(invoice);
	}

	// Yeni fatura ekle
	@PostMapping
	public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
		InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
		return ResponseEntity.ok(createdInvoice);
	}

	// Faturayı güncelle
	@PutMapping("/{id}")
	public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
		InvoiceDTO updatedInvoice = invoiceService.updateInvoice(id, invoiceDTO);
		return ResponseEntity.ok(updatedInvoice);
	}

	// Faturayı sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
		invoiceService.deleteInvoice(id);
		return ResponseEntity.noContent().build();
	}
}
