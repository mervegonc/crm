package com.project.crm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.crm.dto.OrderDTO;
import com.project.crm.entity.Order;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.repository.ProductRepository;

@Component
public class OrderMapper {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	public OrderDTO toDTO(Order order) {
		return new OrderDTO(order.getId(), order.getProduct().getName(), // Ürünün ismi alınıyor
				order.getQuantity(), order.getTotalPrice(), null, 0, null);
	}

	public Order toEntity(OrderDTO orderDTO) {
		Order order = new Order();
		order.setId(orderDTO.getId());
		order.setProduct(productRepository.findByName(orderDTO.getProduct()).orElse(null)); // Ürünün ismi kullanılarak
																							// Product bulunuyor
		order.setQuantity(orderDTO.getQuantity());
		order.setTotalPrice(orderDTO.getPrice());
		return order;
	}
}
