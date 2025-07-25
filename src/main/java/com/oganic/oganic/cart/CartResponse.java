package com.oganic.oganic.cart;

import com.oganic.oganic.product.ProductResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {
    private Long id;
    private ProductResponse product;
    private Integer quatity;
    
}
