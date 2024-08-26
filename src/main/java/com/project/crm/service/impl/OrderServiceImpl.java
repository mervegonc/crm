package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.dto.OrderDTO;
import com.project.crm.entity.Order;
import com.project.crm.mapper.OrderMapper;
import com.project.crm.repository.OrderRepository;
import com.project.crm.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public List<Order> findByCustomerId(Long customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> findByStatus(String status) {
		return orderRepository.findByStatus(status);
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}

	// Eksik metotlar
	@Override
	public List<OrderDTO> getAllOrders() {
		return orderRepository.findAll().stream().map(orderMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public OrderDTO getOrderById(Long id) {
		return orderRepository.findById(id).map(orderMapper::toDTO)
				.orElseThrow(() -> new RuntimeException("Order not found"));
	}

	@Override
	public OrderDTO createOrder(OrderDTO orderDTO) {
		Order order = orderMapper.toEntity(orderDTO);
		order = orderRepository.save(order);
		return orderMapper.toDTO(order);
	}

	@Override
	public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
		Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

		existingOrder.setQuantity(orderDTO.getQuantity());
		existingOrder.setOrderDate(orderDTO.getOrderDate());
		existingOrder.setStatus(orderDTO.getStatus());
		existingOrder.setTotalPrice(orderDTO.getTotalPrice());

		existingOrder = orderRepository.save(existingOrder);
		return orderMapper.toDTO(existingOrder);
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}
}
