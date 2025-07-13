package com.oganic.oganic.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Product> getProductsPageable(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return productRepository.findAll(pageable);
	}
	
	
	public List<Product> getLatestProducts() {
		return productRepository.findAll(Sort.by("createdAt"));
	}

	public List<Product> getDiscountProducts() {
		return productRepository.findDiscount();
	}
}
