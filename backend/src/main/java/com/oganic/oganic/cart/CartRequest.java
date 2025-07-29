package com.oganic.oganic.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quatity;
}
