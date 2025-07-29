package com.oganic.oganic.order;

import com.oganic.oganic.product.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailResponse {
    private Long id;
    private Long orderId;
    private ProductResponse product;
    private Integer quatity;
}
