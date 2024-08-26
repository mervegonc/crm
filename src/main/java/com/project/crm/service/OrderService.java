package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.dto.OrderDTO;
import com.project.crm.entity.Order;

public interface OrderService {

	Optional<Order> findById(Long id);

	List<Order> findByCustomerId(Long customerId);

	List<Order> findAll();

	List<Order> findByStatus(String status);

	Order save(Order order);

	void deleteById(Long id);

	// Eksik metotlar
	List<OrderDTO> getAllOrders();

	OrderDTO getOrderById(Long id);

	OrderDTO createOrder(OrderDTO orderDTO);

	OrderDTO updateOrder(Long id, OrderDTO orderDTO);

	void deleteOrder(Long id);
}
