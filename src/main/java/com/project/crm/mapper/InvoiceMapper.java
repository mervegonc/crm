package com.project.crm.mapper;

import org.springframework.stereotype.Component;

import com.project.crm.dto.InvoiceDTO;
import com.project.crm.entity.Invoice;

@Component
public class InvoiceMapper {

	public InvoiceDTO toDTO(Invoice invoice) {
		return new InvoiceDTO(invoice.getId(), invoice.getOrder().getId(), invoice.getInvoiceDate(),
				invoice.getDueDate(), invoice.getStatus(), invoice.getTotalAmount(), invoice.getCreatedAt(),
				invoice.getUpdatedAt());
	}

	public Invoice toEntity(InvoiceDTO invoiceDTO) {
		Invoice invoice = new Invoice();
		invoice.setId(invoiceDTO.getId());
		invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
		invoice.setDueDate(invoiceDTO.getDueDate());
		invoice.setStatus(invoiceDTO.getStatus());
		invoice.setTotalAmount(invoiceDTO.getTotalAmount());
		invoice.setCreatedAt(invoiceDTO.getCreatedAt());
		invoice.setUpdatedAt(invoiceDTO.getUpdatedAt());
		return invoice;
	}
}
