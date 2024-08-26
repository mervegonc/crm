package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	Optional<Invoice> findByOrderId(Long orderId);

	List<Invoice> findByStatus(String status);

	List<Invoice> findByOrderByDueDateAsc();

	List<Invoice> findByCustomerId(Long customerId);

	List<Invoice> findByInvoiceDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<Invoice> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
