package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.dto.InvoiceDTO;
import com.project.crm.entity.Invoice;

public interface InvoiceService {

	Optional<Invoice> findById(Long id);

	Optional<Invoice> findByOrderId(Long orderId);

	List<Invoice> findAll();

	List<Invoice> findByStatus(String status);

	Invoice save(Invoice invoice);

	void deleteById(Long id);

	// Eksik metotlar
	List<InvoiceDTO> getAllInvoices();

	InvoiceDTO getInvoiceById(Long id);

	InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

	InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);

	void deleteInvoice(Long id);
}
