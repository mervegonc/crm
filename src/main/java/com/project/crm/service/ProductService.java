package com.project.crm.service;

import java.util.List;
import java.util.Optional;

import com.project.crm.dto.ProductDTO;
import com.project.crm.entity.Product;

public interface ProductService {

	Optional<Product> findById(Long id);

	Optional<Product> findByName(String name);

	List<Product> findAll();

	Product save(Product product);

	void deleteById(Long id);

	List<ProductDTO> getAllProducts();

	ProductDTO getProductById(Long id);

	ProductDTO createProduct(ProductDTO productDTO);

	ProductDTO updateProduct(Long id, ProductDTO productDTO);

	void deleteProduct(Long id);
}
