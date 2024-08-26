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

import com.project.crm.dto.ProductDTO;
import com.project.crm.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// Tüm ürünleri listele
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	// ID'ye göre ürünü getir
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
		ProductDTO product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}

	// Yeni ürün ekle
	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
		ProductDTO createdProduct = productService.createProduct(productDTO);
		return ResponseEntity.ok(createdProduct);
	}

	// Ürünü güncelle
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
		return ResponseEntity.ok(updatedProduct);
	}

	// Ürünü sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
