package com.project.crm.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByName(String name);

	List<Product> findByStockGreaterThan(Integer stock);

	List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

	List<Product> findByActiveTrue();

	List<Product> findByOrderByCreatedAtDesc();

	List<Product> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
