package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.dto.InvoiceDTO;
import com.project.crm.entity.Invoice;
import com.project.crm.mapper.InvoiceMapper;
import com.project.crm.repository.InvoiceRepository;
import com.project.crm.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceMapper invoiceMapper;

	@Override
	public Optional<Invoice> findById(Long id) {
		return invoiceRepository.findById(id);
	}

	@Override
	public Optional<Invoice> findByOrderId(Long orderId) {
		return invoiceRepository.findByOrderId(orderId);
	}

	@Override
	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public List<Invoice> findByStatus(String status) {
		return invoiceRepository.findByStatus(status);
	}

	@Override
	public Invoice save(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public void deleteById(Long id) {
		invoiceRepository.deleteById(id);
	}

	// Eksik metotlar
	@Override
	public List<InvoiceDTO> getAllInvoices() {
		return invoiceRepository.findAll().stream().map(invoiceMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public InvoiceDTO getInvoiceById(Long id) {
		return invoiceRepository.findById(id).map(invoiceMapper::toDTO)
				.orElseThrow(() -> new RuntimeException("Invoice not found"));
	}

	@Override
	public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
		invoice = invoiceRepository.save(invoice);
		return invoiceMapper.toDTO(invoice);
	}

	@Override
	public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) {
		Invoice existingInvoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Invoice not found"));

		existingInvoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
		existingInvoice.setDueDate(invoiceDTO.getDueDate());
		existingInvoice.setStatus(invoiceDTO.getStatus());
		existingInvoice.setTotalAmount(invoiceDTO.getTotalAmount());

		existingInvoice = invoiceRepository.save(existingInvoice);
		return invoiceMapper.toDTO(existingInvoice);
	}

	@Override
	public void deleteInvoice(Long id) {
		invoiceRepository.deleteById(id);
	}
}
