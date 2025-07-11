package com.oganic.oganic.product;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getProducts() {
		
		return productRepository.findAll();
	}
	
	public List<Product> getLatestProducts() {
		return productRepository.findAll(Sort.by("createdAt"));
	}
}
