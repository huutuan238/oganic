package com.oganic.oganic.batchprocessing;

import org.springframework.batch.item.ItemProcessor;

import com.oganic.oganic.product.Product;

public class ProductItemProcessor implements ItemProcessor<Product, Product> {
	
	@Override
	public Product process(final Product product) {
		product.setName(product.getName().toUpperCase());
		product.setDescription(product.getDescription().toUpperCase());
		return product;
	}
}
