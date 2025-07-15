package com.oganic.oganic.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Long userId;
    private Long productId;
    private Integer quatity;
}
