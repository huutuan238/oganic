package com.oganic.oganic.product;

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

	private String description;

	private Double price;

	private Integer stock;

	private String image;

	private Double discount;
}
