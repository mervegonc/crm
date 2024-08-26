package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByCustomerId(Long customerId);

	List<Order> findByStatus(String status);

	List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<Order> findByOrderByTotalPriceDesc();

	List<Order> findByProductId(Long productId);

	List<Order> findByTotalPriceGreaterThan(Double totalPrice);
}
