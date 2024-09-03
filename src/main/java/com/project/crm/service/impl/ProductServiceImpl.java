package com.project.crm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.dto.ProductDTO;
import com.project.crm.entity.Product;
import com.project.crm.repository.ProductRepository;
import com.project.crm.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Optional<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	// Controller'da kullanılan yeni methodlar
	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream()
				.map(product -> new ProductDTO(product.getId(), product.getName(), null, product.getPrice()))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		return new ProductDTO(product.getId(), product.getName(), null, product.getPrice());
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		Product savedProduct = productRepository.save(product);
		return new ProductDTO(savedProduct.getId(), savedProduct.getName(), null, savedProduct.getPrice());
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		Product updatedProduct = productRepository.save(product);
		return new ProductDTO(updatedProduct.getId(), updatedProduct.getName(), null, updatedProduct.getPrice());
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
