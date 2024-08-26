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

import com.project.crm.dto.OrderDTO;
import com.project.crm.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// Tüm siparişleri listele
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderDTO> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	// ID'ye göre siparişi getir
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
		OrderDTO order = orderService.getOrderById(id);
		return ResponseEntity.ok(order);
	}

	// Yeni sipariş ekle
	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
		OrderDTO createdOrder = orderService.createOrder(orderDTO);
		return ResponseEntity.ok(createdOrder);
	}

	// Siparişi güncelle
	@PutMapping("/{id}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
		OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
		return ResponseEntity.ok(updatedOrder);
	}

	// Siparişi sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}
}
