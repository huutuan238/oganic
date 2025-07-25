package com.oganic.oganic.product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> getRelatedProducts(Long catogoryId);
}
