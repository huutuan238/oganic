package com.oganic.oganic.product;

import java.util.Locale.Category;

import com.oganic.oganic.category.CategoryResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;

	private String name;

	private CategoryResponse category;

	private String description;

	private Double price;

	private Integer stock;

	private String image;

	private Double discount;
}
