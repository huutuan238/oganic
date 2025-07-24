package com.oganic.oganic.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class ProductSeriveTest {
    @Autowired
    private ProductService productService;

    @MockitoBean
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Apple");
        p1.setPrice(1.23);
        when(productRepository.findAll()).thenReturn(List.of(p1));

        List<Product> result = productService.getProducts();

        assertEquals(1, result.size());
        assertEquals("Apple", result.get(0).getName());
    }

    @Test
    public void testGetProductDetail() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Apple");
        p1.setPrice(1.23);
        when(productRepository.findById(p1.getId())).thenReturn(Optional.of(p1));

        Product product = productService.getById(p1.getId());
        assertEquals(p1, product);
    }
}
